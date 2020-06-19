package com.dxt.ht.svc.form.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.form.dao.HtRepairOfferPartMapper;
import com.dxt.ht.svc.form.entity.vo.HtRepairOfferPartVO;
import com.dxt.ht.svc.policy.dao.HtPolicyInfoMapper;
import com.dxt.ht.svc.policy.entity.HtPolicyInfo;
import com.dxt.ht.svc.policy.entity.vo.HtPolicyInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 维修部件Service
 *
 * @author wangfanglin
 * @date 2020/04/01
 */


@RestController
@Api(tags = "维修模块")
@RequestMapping(value = "api/assembly/unit")
public class HtAssemblyUnitService {

    @Autowired
    private HtPolicyInfoMapper htPolicyInfoMapper;

    @Autowired
    HtRepairOfferPartMapper htRepairOfferPartMapper;

    /**
     * 查询维修方案-维修
     * @param id 工单id
     * @return
     */
    @GetMapping("getMaintenancePlan")
    @ApiOperation(value = "查询维修方案",notes = "查询维修方案")
    @ApiImplicitParam(name = "id",value = "工单id",dataType="string", paramType = "query",required = true)
    public ApiResult<HtPolicyInfoVO> getAssemblyUnit(String id){
        HtPolicyInfo htPolicyInfo = htPolicyInfoMapper.selectByFormId(id);
        List<HtRepairOfferPartVO> htRepairOfferPartVOs = htRepairOfferPartMapper.selectByFormId(id);
        if(CollectionUtils.isEmpty(htRepairOfferPartVOs) || ObjectUtils.isEmpty(htPolicyInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        HtPolicyInfoVO htPolicyInfoVO = new HtPolicyInfoVO();
        htPolicyInfoVO.setProName(htPolicyInfo.getHtChannelProductInfo().getName());
        htPolicyInfoVO.setModel(htPolicyInfo.getHtPhoneModelInfo().getModel());
        htPolicyInfoVO.setName(htPolicyInfo.getHtBrandInfo().getName());
        htPolicyInfoVO.setHtRepairOfferPartVOs(htRepairOfferPartVOs);
        htPolicyInfoVO.setHtFormInfo(htPolicyInfo.getHtFormInfo());
        return ApiResultFactory.getSuccess(htPolicyInfoVO);
    }
public static void main(String[] args){
    BinaryOperator<Integer> add = (n1,n2) -> n1 + n2;
    System.out.println(add.apply(1,2));
}




}
