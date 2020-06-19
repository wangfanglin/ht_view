package com.dxt.ht.svc.policy.service;

import com.dxt.ht.svc.apply.dao.HtUserApplyInfoDao;
import com.dxt.ht.svc.apply.entity.HtUserApplyInfo;
import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.form.dao.HtFormInfoMapper;
import com.dxt.ht.svc.form.entity.HtFormInfo;
import com.dxt.ht.svc.policy.dao.HtPolicyInfoMapper;
import com.dxt.ht.svc.policy.entity.HtPolicyInfo;
import com.dxt.ht.svc.policy.entity.PolicyInfoListEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "保单模块")
@RequestMapping("api/policy")
public class HtPolicyInfoService {

    @Autowired
    private HtPolicyInfoMapper htPolicyInfoMapper;
    @Autowired
    private HtUserApplyInfoDao htUserApplyInfoDao;
    @Autowired
    private HtFormInfoMapper htFormInfoMapper;

    @GetMapping("getPolicyInfo")
    @ApiOperation(value = "获取保单信息", notes = "获取保单信息")
    @ApiImplicitParam(name = "policyId", value = "保单id", dataType = "string", paramType = "query")
    public ApiResult<HtPolicyInfo> getPolicyInfo(String policyId) {
        if (StringUtils.isNotBlank(policyId)) {
            HtPolicyInfo htPolicyInfo = htPolicyInfoMapper.selectByPrimaryKey(policyId);
            HtUserApplyInfo applyStatus = htUserApplyInfoDao.getApplyStatus(policyId);
            String policyStatus = htPolicyInfo.getPolicyStatus();
            //判断保险还在保障中
            if (htPolicyInfo != null) {
                if (StringUtils.isNotBlank(policyStatus) && "1".equals(policyStatus)) {
                    //判断没有申请过保险
                    if (applyStatus == null) {
                        htPolicyInfo.setApplyStatus("1");
                        return ApiResultFactory.getSuccess(htPolicyInfo);
                        //判断已经申请过，还未走流程
                    } else if (StringUtils.isNotBlank(applyStatus.getId()) && StringUtils.isBlank(applyStatus.getFormId())) {
                        htPolicyInfo.setApplyStatus("0");
                        return ApiResultFactory.getSuccess(htPolicyInfo);
                        //判断已经申请过，当前得流程状态
                    } else if (StringUtils.isNotBlank(applyStatus.getId()) && StringUtils.isNotBlank(applyStatus.getFormId())) {
                        HtFormInfo htFormInfo = htFormInfoMapper.selectByPrimaryKey(applyStatus.getFormId());
                        if (htFormInfo != null &&
                                ("0".equals(htFormInfo.getIsRun()) && "1014".equals(htFormInfo.getFormStatus())
                                        || "1".equals(htFormInfo.getIsRun()))) {
                            htPolicyInfo.setApplyStatus("1");
                            return ApiResultFactory.getSuccess(htPolicyInfo);
                        } else {
                            htPolicyInfo.setApplyStatus("0");
                            return ApiResultFactory.getSuccess(htPolicyInfo);
                        }
                    } else {
                        htPolicyInfo.setApplyStatus("0");
                        return ApiResultFactory.getSuccess(htPolicyInfo);
                    }
                } else {
                    htPolicyInfo.setApplyStatus("0");
                    return ApiResultFactory.getSuccess(htPolicyInfo);
                }
            } else {
                return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "没有该保单!");
            }
        } else {
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "保单id不能为空");
        }

    }


    @GetMapping("getPolicyInfoList")
    @ApiOperation(value = "获取保单集合信息", notes = "获取保单集合信息")
    public ApiResult<List<HtPolicyInfo>> getPolicyInfoList(@Valid PolicyInfoListEntity policyInfoListEntity) {
        List<HtPolicyInfo> policyInfoList = htPolicyInfoMapper.findPolicyList(policyInfoListEntity);
        System.out.println(policyInfoList);
        if (policyInfoList != null && policyInfoList.size() > 0)
            return ApiResultFactory.getSuccess(policyInfoList);
        else
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "没有查到当前用户订单");
    }


}
