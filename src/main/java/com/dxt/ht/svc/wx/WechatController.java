package com.dxt.ht.svc.wx;

import com.aliyun.openservices.shade.io.netty.util.internal.StringUtil;
import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.common.utils.ID;
import com.dxt.ht.svc.form.dao.HtDeductibleInfoMapper;
import com.dxt.ht.svc.form.entity.HtDeductibleInfo;
import com.dxt.ht.svc.redis.RedisUtil;
import com.dxt.ht.svc.user.dao.HtUserInfoDao;
import com.dxt.ht.svc.user.entity.HtUserInfo;
import com.dxt.ht.svc.wx.pay.WxConfig;
import com.dxt.ht.svc.wx.pay.WxPay;
import com.dxt.ht.svc.wx.pay.WxPayParam;
import com.dxt.pass.Channel;
import com.dxt.pass.Company;
import com.dxt.pass.cargo.PlaceOrderParameter;
import com.dxt.pass.entity.Result;
import com.dxt.pass.harvest.PostOrderResult;
import com.dxt.pass.wx.WxPlaceOrderResult;
import com.dxt.pass.wx.WxTools;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import static com.dxt.ht.svc.wx.WeixinUtil.getOauth2AccessToken;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.modules.wechat
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/11/19 2:16 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/11/19 2:16 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Slf4j
@RestController
@RequestMapping("api/wechat")
@Api(tags = "微信模块")
public class WechatController {
    @Autowired
    Constant constant;

    @Autowired
    WeixinUtil weixinUtil;
    @Autowired
    HtUserInfoDao htUserInfoDao;
    @Autowired
    WxPay wxPay;
    @Autowired
    WxConfig wxConfig;

    @Autowired
    private HtDeductibleInfoMapper htDeductibleInfoMapper;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取应用appId", notes = "获取应用appId")
    @GetMapping("navigationMap")
    @ResponseBody
    public ApiResult<Map<String, Object>> getNavigationMap(@ApiParam(name = "url", value = "") String url) {
        System.out.println("111");
        Map<String, Object> stringObjectMap = null;
        try {
            stringObjectMap = weixinUtil.navigationMap(url, constant.getWechatAppId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("stringObjectMap：" + stringObjectMap);
        return ApiResultFactory.getSuccess(stringObjectMap);
    }


    @SuppressWarnings("unchecked")
    @ApiOperation(value = "根据code获取微信用户信息", notes = "根据code获取微信用户信息")
    @GetMapping("getWeixinOpenId")
    @ResponseBody
    public ApiResult<Map<String, Object>> getWeixinOpenId(@ApiParam(name = "code", value = "code") String code) throws Exception {
        Map<String, Object> ApiResult = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(code)) {
            //2.通过code获取到openid
            Map<String, Object> map = getOauth2AccessToken(new Oauth2CodePojo(constant.getWechatAppId(), constant.getWechatAppSecret(), code));
            log.warn("根据code获取到的openid======>" + map.toString());
            String openid = (String) map.get("openid");
            String access_token = (String) map.get("access_token");
            Map<String, Object> wxUserInfo = WeixinUtil.getWeixinUserInfo(openid, access_token);
            log.warn("根据openid获取到的微信用户信息======>" + wxUserInfo.toString());

            return ApiResultFactory.getSuccess(wxUserInfo);
        } else {
            log.error("code值不能为空");
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM);
        }

    }

    @ApiOperation(value = "微信支付", notes = "微信支付")
    @PostMapping("wxPayOrder")
    public ApiResult wxPayOrder(@RequestBody @Valid WxPayParam wxPayParam) {
        //查询自付款信息
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formId", wxPayParam.getFormId());
        List<HtDeductibleInfo> htDeductibleInfoList = htDeductibleInfoMapper.select(hashMap);
        HtDeductibleInfo htDeductibleInfo = null;
        if (htDeductibleInfoList != null && htDeductibleInfoList.size() > 0) {
            htDeductibleInfo = htDeductibleInfoList.get(0);
            BigDecimal deductiblesLimit = htDeductibleInfo.getDeductiblesLimit();
            BigDecimal paramDuctil = new BigDecimal(wxPayParam.getTotalFee());
            if (deductiblesLimit.compareTo(paramDuctil) != 0) {
                return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "传入金额出错");
            }
            //初始化自付款修改信息
            HtUserInfo userInfo = (HtUserInfo) htUserInfoDao.selectById(wxPayParam.getOpenid());
            htDeductibleInfo.setOpenId(wxPayParam.getOpenid());
            htDeductibleInfo.setPayType("wx");
            htDeductibleInfo.setPayStatus("1");
            htDeductibleInfo.setWxNick(userInfo.getUserName());
            htDeductibleInfoMapper.updateByPrimaryKey(htDeductibleInfo);
        }

        Map<String, String> payResultMap = wxPay.payment(wxPayParam);
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        if (payResultMap.get("return_code").equals("SUCCESS") &&
                payResultMap.get("return_msg").equals("OK")) {
            //应用ID
            finalpackage.put("appId", payResultMap.get("appid"));
            //时间戳
            Long time = (System.currentTimeMillis() / 1000);
            finalpackage.put("timeStamp", time.toString());
            //随机字符串
            finalpackage.put("nonceStr", WXPayUtil.generateNonceStr());
            //signType
            finalpackage.put("signType", "MD5");
            //扩展字段
            finalpackage.put("package", "prepay_id=" + payResultMap.get("prepay_id"));
            //paySign
            try {
                finalpackage.put("paySign", WXPayUtil.generateSignature(finalpackage, wxConfig.getKey()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            return ApiResultFactory.newInstance(ResultStatus.SYSTEM_ERROR, payResultMap.get("return_msg"));
        }
        if (payResultMap.get("result_code").equals("FAIL")) {
            return ApiResultFactory.newInstance(ResultStatus.SYSTEM_ERROR, payResultMap.get("err_code_des"));
        }
        return ApiResultFactory.getSuccess(finalpackage);
    }

    @ApiOperation(value = "微信支付回调", notes = "微信支付回调")
    @PostMapping("wxPayCallBack")
    public String wxPayCallBack(HttpServletRequest request, HttpServletResponse response) {
        log.info("微信支付回调");
        Map<String, String> return_data = new HashMap<String, String>();
        return_data.put("return_code", "SUCCESS");
        return_data.put("return_msg", "OK");
        String returnXml = "";
        try {
            returnXml = WXPayUtil.mapToXml(return_data);
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            String resultxml = new String(outSteam.toByteArray(), "utf-8");
            outSteam.close();
            inStream.close();
            log.info("resultXmlDate" + resultxml);
            //签名验证,并校验返回的订单金额是否与商户侧的订单金额一致
            if (WXPayUtil.isSignatureValid(resultxml, wxConfig.getKey())) {
                log.info("===============签名验证通过");
                Map<String, String> callBackDate = WXPayUtil.xmlToMap(resultxml);
                if (callBackDate.get("return_code").equals("SUCCESS")) {
                    log.info("===============return_code   SUCCESS");
                    String totalFee = callBackDate.get("total_fee");
                    String outId = callBackDate.get("attach");
                    HtDeductibleInfo htDeductibleInfo = htDeductibleInfoMapper.selectByPrimaryKey(outId);
                    BigDecimal deductiblesLimit = htDeductibleInfo.getDeductiblesLimit();
                    BigDecimal totalFeeDec = new BigDecimal(totalFee);
                    //if (deductiblesLimit.compareTo(totalFeeDec) == 0) {
                        log.info("===============订单金额与商户侧的订单金额一致");
                        String status = "0";
                        htDeductibleInfoMapper.updateStatus(outId, status);
                    //}
                }
            }
            wxPay.saveCallBackLog(resultxml);
            log.info("===============" + resultxml);
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            return returnXml;
        }
    }


    public static void main(String[] args) {
        BigDecimal b1 = BigDecimal.valueOf(1);
        BigDecimal totalFeeDec = new BigDecimal("1");
        if (b1.compareTo(totalFeeDec) == 0) {
            System.out.println("===============订单金额与商户侧的订单金额一致");
        }
    }

}
