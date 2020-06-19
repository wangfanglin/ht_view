package com.dxt.ht.svc.form.service;


import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.form.dao.HtDeductibleInfoMapper;
import com.dxt.ht.svc.form.entity.HtDeductibleInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自付款接口
 *
 * @author wangfanglin
 * @2020/04/13
 */
@RestController
@Api(tags = "自付款模块")
@RequestMapping(value = "api/ht/deductible/info")
public class HtDeductibleInfoService {

    @Autowired
    private HtDeductibleInfoMapper htDeductibleInfoMapper;

    @GetMapping("getHtDeductibleInfoList")
    @ApiOperation(value = "自付款查询接口",notes = "自付款查询接口")
    @ApiImplicitParam(name = "formId",value = "工单id",dataType="string", paramType = "query")
    public ApiResult<HtDeductibleInfo> getHtDeductibleInfoList(String formId){
        Map<String,String> params = new HashMap<>();
        params.put("formId",formId);
        List<HtDeductibleInfo> htDeductibleInfoList = htDeductibleInfoMapper.select(params);
        if(CollectionUtils.isEmpty(htDeductibleInfoList)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "查询数据为空");
        }
        return ApiResultFactory.getSuccess(htDeductibleInfoList.stream().findFirst().get());
    }
}
