package com.dxt.ht.svc.aliyun;

import com.dxt.ht.svc.aliyun.entity.IdCardInformation;
import com.dxt.ht.svc.aliyun.utils.ALliYunUtils;
import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行卡识别接口
 *
 * @author wangfanglin
 * @since 2020/04/24
 */
@RestController
@Api(tags = "银行卡识别")
public class BankCardIdentification {

    @GetMapping(value = "getBankCardIdentification")
    @ApiOperation(value = "银行卡识别",notes = "银行卡识别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "acct_no",value = "银行卡号",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name = "idcard",value = "身份证号",dataType="string", paramType = "query",required = false),
            @ApiImplicitParam(name = "name",value = "持卡人姓名",dataType="string", paramType = "query")})
    public ApiResult<Map> getIdentification(String acct_no, String idcard,String name){
        try {
            Map map = ALliYunUtils.getBankCardIdentification(acct_no,idcard,name);
            if("10001".equals(map.get("code"))){
                return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM,map.get("message").toString());
            }
            return ApiResultFactory.getSuccess(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "getBankCardInformation")
    @ApiOperation(value = "银行卡识别信息",notes = "银行卡识别信息")
    @ApiImplicitParam(name = "url",value = "银行卡图片路径",dataType="string", paramType = "query")
    public ApiResult<Map> getBankCardInformation(String url){
        try {
            //Map map = ALliYunUtils.getBankCardIdentification(url);
            Map map = getInfo();
            return ApiResultFactory.getSuccess(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map getInfo() {
        Map map = new HashMap();
        map.put("bank_name","中国银行");
        map.put("card_num","6227594409463185");
        map.put("valid_date","03/17");
        map.put("card_type","DC");
        map.put("request_id","20190806163620_e8b744b981620b764c4abb9537533287");
        map.put("success","true");
        return map;
    }
}
