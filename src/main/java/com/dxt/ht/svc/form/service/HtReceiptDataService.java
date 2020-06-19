package com.dxt.ht.svc.form.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.common.utils.DateUtil;
import com.dxt.ht.svc.form.dao.HtClaimDisqualificationLogMapper;
import com.dxt.ht.svc.form.dao.HtReceiptDataMapper;
import com.dxt.ht.svc.form.entity.HtClaimDisqualificationLog;
import com.dxt.ht.svc.form.entity.HtReceiptData;
import com.dxt.ht.svc.policy.dao.HtPolicyInfoMapper;
import com.dxt.ht.svc.policy.entity.HtPolicyInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "全损录入打款信息")
@RequestMapping("api/receiptData")
public class HtReceiptDataService {

    @Autowired
    private HtPolicyInfoMapper htPolicyInfoMapper;

    @Autowired
    private HtReceiptDataMapper htReceiptDataMapper;

    @Autowired
    private HtClaimDisqualificationLogMapper htClaimDisqualificationLogMapper;

    @GetMapping("getReceiptData")
    @ApiOperation(value = "获取打款信息",notes = "获取打款信息")
    @ApiImplicitParam(name = "id",value = "工单id",dataType="string", paramType = "query",required = true)
    public ApiResult<HtPolicyInfo> getFormRateProgress(String id){
        HtPolicyInfo htPolicyInfo = htPolicyInfoMapper.selectByFormId(id);
        if(ObjectUtils.isEmpty(htPolicyInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        return ApiResultFactory.getSuccess(htPolicyInfo);
    }

    @PostMapping("saveReceiptData")
    @ApiOperation(value = "保存打款信息",notes = "保存打款信息")
    public ApiResult<HtReceiptData> saveReceiptData (@RequestBody HtReceiptData htReceiptData){
        htReceiptData.setAuditStatus("0");
        htReceiptData.setStatus("1");
        htReceiptData.setApplyDate(DateUtil.nowTime());
        int insertRows = htReceiptDataMapper.updateByFormId(htReceiptData);
        if(insertRows == 0){
            return ApiResultFactory.newInstance(ResultStatus.SYSTEM_ERROR,"插入数据失败");
        }
        return ApiResultFactory.getSuccess();
    }

    @GetMapping("getReceiptDataSubmitForReview")
    @ApiOperation(value = "提交待审核页面",notes = "提交待审核页面")
    @ApiImplicitParam(name = "id",value = "工单id",dataType="string", paramType = "query",required = true)
    public ApiResult<HtPolicyInfo> saveReceiptData(String id){
        HtPolicyInfo htPolicyInfo = htPolicyInfoMapper.selectReceiptByFormId(id);
        if(ObjectUtils.isEmpty(htPolicyInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        return ApiResultFactory.getSuccess(htPolicyInfo);
    }

    @PostMapping("ReviseData")
    @ApiOperation(value = "重新修改资料",notes = "重新修改资料")
    @ApiImplicitParam(name = "id",value = "工单id",dataType="string", paramType = "query",required = true)
    public ApiResult<HtPolicyInfo> ReviseData (String id){
        HtPolicyInfo htPolicyInfo = htPolicyInfoMapper.selectReceiptByFormId(id);
        HtClaimDisqualificationLog htClaimDisqualificationLog = htClaimDisqualificationLogMapper.selectByFormId(id);
        htPolicyInfo.setHtClaimDisqualificationLog(htClaimDisqualificationLog);
        if(ObjectUtils.isEmpty(htPolicyInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        return ApiResultFactory.getSuccess(htPolicyInfo);
    }

}
