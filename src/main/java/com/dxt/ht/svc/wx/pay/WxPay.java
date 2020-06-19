package com.dxt.ht.svc.wx.pay;

import com.alibaba.fastjson.JSON;
import com.dxt.ht.svc.wx.log.dao.HtPayLogMapper;
import com.dxt.ht.svc.wx.log.entity.HtPayLogWithBLOBs;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
public class WxPay {

    @Autowired
    HtPayLogMapper htPayLogMapper;
    @Autowired
    WxConfig wxConfig;
    /**
     * 整合支付所需参数进行支付
     *
     * @param wxPayParam
     * @return
     */
    public Map<String, String> payment(WxPayParam wxPayParam) {
        //初始化支付所需信息
        WxConfig wxConfig = this.wxConfig;
        WXPay wxpay = new WXPay(wxConfig);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "HeDeXinTong Since the payment");
        data.put("out_trade_no", wxPayParam.getOutTradeNo());
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        //data.put("total_fee", wxPayParam.getTotalFee());
        data.put("openid", wxPayParam.getOpenid());
        data.put("spbill_create_ip", wxPayParam.getSpbillCreateIp());
        data.put("notify_url", "http://testht.site.hollardchina.com.cn/ht-svc/api/wechat/wxPayCallBack");
        data.put("trade_type", "JSAPI");
        data.put("attach", wxPayParam.getOutTradeNo());
        //调用支付返回结果
        Map<String, String> resp = new HashMap<>();
        //添加支付日志
        HtPayLogWithBLOBs htPayLogWithBLOBs = new HtPayLogWithBLOBs();
        htPayLogWithBLOBs.setMethods("wxPay");
        htPayLogWithBLOBs.setOrderId(wxPayParam.getOutTradeNo());
        try {
            resp = wxpay.unifiedOrder(data);
            htPayLogWithBLOBs.setParam(JSON.toJSONString(data));
            htPayLogWithBLOBs.setResult(JSON.toJSONString(resp));
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            htPayLogMapper.insertSelective(htPayLogWithBLOBs);
        }
        return resp;
    }

    /**
     * 新增日志
     * @param resultxml
     */
    public void saveCallBackLog(String resultxml) {
        //添加支付日志
        HtPayLogWithBLOBs htPayLogWithBLOBs = new HtPayLogWithBLOBs();
        htPayLogWithBLOBs.setMethods("wxPay");
        htPayLogWithBLOBs.setResult(JSON.toJSONString(resultxml));
        htPayLogMapper.insertSelective(htPayLogWithBLOBs);
    }
}
