package com.dxt.ht.svc.form.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.common.utils.DateUtil;
import com.dxt.ht.svc.common.utils.HttpReqUtils;
import com.dxt.ht.svc.form.dao.*;
import com.dxt.ht.svc.form.entity.*;
import com.dxt.ht.svc.form.entity.vo.FormProgressVO;
import com.dxt.ht.svc.form.entity.vo.HtClaimSettlementFormVO;
import com.dxt.ht.svc.sf.service.SfOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RestController
@Api(tags = "工单模块")
@RequestMapping("api/formInfo")
@Slf4j
public class HtHistoryService {

    @Autowired
    private HtFormInfoMapper htFormInfoMapper;
    @Autowired
    private HtHistoryMapper historyMapper;

    @Autowired
    private HtClaimSettlementFormMapper htClaimSettlementFormMapper;

    @Autowired
    private HtAssemblyUnitMapper htAssemblyUnitMapper;

    @Autowired
    private HtRepairClientFormMapper htRepairClientFormMapper;
    @Autowired
    private HtReceiptDataMapper htReceiptDataMapper;
    @Autowired
    private HtDeductibleInfoMapper htDeductibleInfoMapper;

    @GetMapping("getFormRateProgress")
    @ApiOperation(value = "获取工单进度",notes = "获取工单进度")
    @ApiImplicitParam(name = "policyId",value = "保单id",dataType="string", paramType = "query")
    public ApiResult<List<FormProgressVO>> getFormRateProgress(String policyId){
        if (!StringUtils.isNotBlank(policyId)){
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM,"工单id不能为空");
        }
        List<HtFormInfo> formList = htFormInfoMapper.findAllFrom(policyId);
        if(CollectionUtils.isEmpty(formList)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "保单id:"+policyId+"下没有工单，请确认该保单是否有效");
        }
        Map<String,String> formMap = formList.stream().collect(Collectors.toMap(HtFormInfo::getId,HtFormInfo::getCreateDate));
        List<String> formIds = formList.stream().map(HtFormInfo::getId).collect(Collectors.toList());
        List<HtHistory> htHistories = historyMapper.selectByIds(formIds);
        if (CollectionUtils.isEmpty(htHistories)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "查询数据为空");
        }
        //根据工单状态拼装文案信息
        formatDataByOperationStatus(htHistories);
        Map<String,List<HtHistory>> hisListMap = htHistories.stream().collect(groupingBy(HtHistory :: getWorkOrderId));
        //返回的集合
        List<FormProgressVO> formProgressVOs = new ArrayList<>();
        List<HtClaimSettlementFormVO> htClaimSettlementFormList = htClaimSettlementFormMapper.selectByIds(formIds);
        if(CollectionUtils.isEmpty(htClaimSettlementFormList)){
            //没有损坏部位拼装历史进度
            formatDataByNoHtClaimSettlementFormList(hisListMap,formProgressVOs,formMap);
        }else {
            //有损坏部位时拼装历史进度和损坏部位
            formatDataByHasHtClaimSettlementFormList(htClaimSettlementFormList,formProgressVOs,hisListMap,formMap);
        }
        //拼装自付款信息
        List<HtDeductibleInfo> htDeductibleInfoList = htDeductibleInfoMapper.selectByFormIds(formIds);
        if(!CollectionUtils.isEmpty(htDeductibleInfoList)){
            formatData(formProgressVOs,htDeductibleInfoList);
        }
        return ApiResultFactory.getSuccess(sortDataByCreateTime(formProgressVOs));
    }



    //拼装损坏部位和历史进度工单
    private void formatDataByHasHtClaimSettlementFormList(List<HtClaimSettlementFormVO> htClaimSettlementFormList, List<FormProgressVO> formProgressVOs, Map<String, List<HtHistory>> hisListMap, Map<String, String> formMap) {
        for (HtClaimSettlementFormVO htClaimSettlementForm : htClaimSettlementFormList) {
            htClaimSettlementForm.setMasterUnitList(Arrays.asList((htClaimSettlementForm.getMasterUnit()).split(",")));
            htClaimSettlementForm.setVicePartsList(Arrays.asList((htClaimSettlementForm.getViceParts()).split(",")));
        }
        List<String> vicePartssList = htClaimSettlementFormList.stream().map(HtClaimSettlementForm::getViceParts).collect(Collectors.toList());
        List<String> masterUnitsList = htClaimSettlementFormList.stream().map(HtClaimSettlementForm::getMasterUnit).collect(Collectors.toList());
        List<String> masterUnitList = new ArrayList<>();
        List<String> vicePartsList = new ArrayList<>();
        masterUnitsList.forEach(e -> {
            String[] split = e.split(",");
            List list = Arrays.asList(split);
            masterUnitList.addAll(list);
        });
        vicePartssList.forEach(e -> {
            String[] split = e.split(",");
            List list = Arrays.asList(split);
            vicePartsList.addAll(list);
        });
        vicePartsList.addAll(masterUnitList);
        if (!CollectionUtils.isEmpty(vicePartsList)) {
            List<HtAssemblyUnit> htAssemblyUnits = htAssemblyUnitMapper.selectByIds(vicePartsList);
            List<HtAssemblyUnit> htAssemblyUnitListOfViceParts = htAssemblyUnits.stream().filter(htAssemblyUnit -> "0".equals(htAssemblyUnit.getMainFlag()) || "0".equals(htAssemblyUnit.getStatus())).collect(Collectors.toList());
            List<HtAssemblyUnit> htAssemblyUnitListOfmasterUnit = htAssemblyUnits.stream().filter(htAssemblyUnit -> "1".equals(htAssemblyUnit.getMainFlag()) || "0".equals(htAssemblyUnit.getStatus())).collect(Collectors.toList());
            Map<String, String> vicePartsNameMap = htAssemblyUnitListOfViceParts.stream().collect(Collectors.toMap(HtAssemblyUnit::getId, HtAssemblyUnit::getName));
            Map<String, String> masterUnitNameMap = htAssemblyUnitListOfmasterUnit.stream().collect(Collectors.toMap(HtAssemblyUnit::getId, HtAssemblyUnit::getName));
            for (HtClaimSettlementFormVO htClaimSettlementForm : htClaimSettlementFormList) {
                List<String> vicePartsNameList = new ArrayList<>();
                for (String viceParts : htClaimSettlementForm.getVicePartsList()) {
                    if (vicePartsNameMap.containsKey(viceParts)) {
                        vicePartsNameList.add(vicePartsNameMap.get(viceParts));
                        htClaimSettlementForm.setVicePartsNameList(vicePartsNameList);
                    }
                }
            }
            for (HtClaimSettlementFormVO htClaimSettlementForm : htClaimSettlementFormList) {
                List<String> masterUnitNameList = new ArrayList<>();
                for (String masterUnit : htClaimSettlementForm.getMasterUnitList()) {
                    if (masterUnitNameMap.containsKey(masterUnit)) {
                        masterUnitNameList.add(masterUnitNameMap.get(masterUnit));
                        htClaimSettlementForm.setMasterUnitNameList(masterUnitNameList);
                    }
                }
            }
            Map<String, HtClaimSettlementFormVO> htClaimSettlementFormMap = htClaimSettlementFormList.stream().collect(Collectors.toMap(HtClaimSettlementFormVO::getFormId, Function.identity(), (HtClaimSettlementFormVO1, HtClaimSettlementFormVO2) -> HtClaimSettlementFormVO2));
            for (String formId : htClaimSettlementFormMap.keySet()) {
                if (hisListMap.containsKey(formId)) {
                    FormProgressVO formProgressVO = new FormProgressVO();
                    formProgressVO.setHisList(hisListMap.get(formId));
                    formProgressVO.setMasterUnit(htClaimSettlementFormMap.get(formId).getMasterUnitNameList());
                    formProgressVO.setViceParts(htClaimSettlementFormMap.get(formId).getVicePartsNameList());
                    formProgressVO.setHtClaimSettlementForm(htClaimSettlementFormMap.get(formId));
                    formProgressVO.setFormId(formId);
                    formProgressVO.setCreateDate(formMap.get(formId));
                    try {
                        //拼装一些其他状态(顺丰邮寄状态，支付状态，审核状态等)
                        formatOtherStatus(formProgressVO, formId);
                    }catch (Exception e){
                        log.error("拼装状态失败",e);
                    }
                    formProgressVOs.add(formProgressVO);
                }
            }
        }
    }

    //根据是否有损坏部位拼装历史进度
    private void formatDataByNoHtClaimSettlementFormList(Map<String,List<HtHistory>> hisListMap, List<FormProgressVO> formProgressVOs,Map<String,String> formMap) {
        for (String key : hisListMap.keySet()) {
            FormProgressVO ProgressVO = new FormProgressVO();
            ProgressVO.setHisList(hisListMap.get(key));
            ProgressVO.setFormId(key);
            ProgressVO.setCreateDate(formMap.get(key));
            try {
                //拼装一些其他状态(顺丰邮寄状态，支付状态，审核状态等)
                formatOtherStatus(ProgressVO, key);
            }catch (Exception e){
                log.error("拼装状态失败",e);
            }
            formProgressVOs.add(ProgressVO);
        }
    }

    //拼装一些其他状态(顺丰邮寄状态，支付状态，审核状态等)
    private void formatOtherStatus(FormProgressVO progressVO, String key) {
        Map<String,String> params = new HashMap<>();
        params.put("formId",key);
        int index = htClaimSettlementFormMapper.findExpressCount(key);
        String sfStatus = index > 0 ? "1" : "0";
        List<HtReceiptData> htReceiptDataList = htReceiptDataMapper.select(params);
        List<HtDeductibleInfo> htDeductibleInfoList = htDeductibleInfoMapper.select(params);
        Optional<HtReceiptData> htReceiptDataOptional = htReceiptDataList.stream().findFirst();
        Optional<HtDeductibleInfo> htDeductibleInfoOptional = htDeductibleInfoList.stream().findFirst();
        progressVO.setSfStatus(sfStatus);
        progressVO.setDudStatus(!htReceiptDataOptional.isPresent() ? "0" : htReceiptDataOptional.get().getStatus());
        progressVO.setPayStatus(!htDeductibleInfoOptional.isPresent() ? "0" : htDeductibleInfoOptional.get().getPayStatus());
        progressVO.setAuditStatus(!htReceiptDataOptional.isPresent() ? null : htReceiptDataOptional.get().getAuditStatus());
    }

    //根据工单状态拼装文案信息
    private void formatDataByOperationStatus(List<HtHistory> htHistories) {
        for (HtHistory htHistory : htHistories) {
            if (htHistory.getOperationStatus().equals("2018") || htHistory.getOperationStatus().equals("2016") || htHistory.getOperationStatus().equals("2014")
                    || htHistory.getOperationStatus().equals("2015") || htHistory.getOperationStatus().equals("2020")){
                String historyFormId = htHistory.getHistoryFormId();
                Map<String,Object> repairClient = historyMapper.getRepairClient(historyFormId);
                String remarks = (String)repairClient.get("remarks");
                String dictRemark = htHistory.getJsSysDictData().getRemarks();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String newRemark = dictRemark.replace("{ENDDATE}",repairClient.get("repair_end_date") == null ? "未知时间" : sdf.format(repairClient.get("repair_end_date")))
                        .replace("{CAUSE}",remarks == null ? "未知原因" : remarks)
                        .replace("{price}",(BigDecimal.valueOf(Double.valueOf(repairClient.get("self_price") == null ? "0" : repairClient.get("self_price").toString())).divide(new BigDecimal(100))).toString());
                htHistory.getJsSysDictData().setRemarks(newRemark);
            }
            if (htHistory.getOperationStatus().equals("2025")){
                List<HtHistory> histories = historyMapper.findHistList(htHistory.getWorkOrderId());
                Optional<HtHistory> first = histories.stream().filter(e -> "2023".equals(e.getOperationStatus())).findFirst();
                String remarks = htHistory.getJsSysDictData().getRemarks();
                if(first.isPresent()){
                    String uniquenessId = first.get().getUniquenessId();
                    String operation = historyMapper.selectFormOperation(uniquenessId);
                    htHistory.getJsSysDictData().setRemarks(remarks.replace("{CAUSE}",operation == null ? "该维修站点没有该配件" : operation));
                }else {
                    //给个默认
                    htHistory.getJsSysDictData().setRemarks(remarks.replace("{CAUSE}", "该维修站点没有该配件"));
                }
            }
            if (htHistory.getOperationStatus().equals("5004")){
                String remarks = htHistory.getJsSysDictData().getRemarks();
                String totalPrice = null;
                if(!StringUtils.isEmpty(htHistory.getWorkOrderId())) {
                    Map<String, String> params = new HashMap<>();
                    params.put("formId", htHistory.getWorkOrderId());
                    List<HtDeductibleInfo> htDeductibleInfos = htDeductibleInfoMapper.select(params);
                    if(!CollectionUtils.isEmpty(htDeductibleInfos)){
                        HtDeductibleInfo htDeductibleInfo = htDeductibleInfos.stream().findFirst().get();
                        totalPrice = htDeductibleInfo.getDeductiblesLimit().divide(new BigDecimal(100)).toString();
                    }

                }
                htHistory.getJsSysDictData().setRemarks(remarks.replace("{price}",totalPrice == null ? "0" : totalPrice));
            }
        }
    }

    //按照创建时间排序
    private List<FormProgressVO> sortDataByCreateTime(List<FormProgressVO> formVOs) {
        if(!CollectionUtils.isEmpty(formVOs)){

        }
        return formVOs.stream().sorted(Comparator.comparing(FormProgressVO::getCreateDate).reversed()).collect(Collectors.toList());
    }

    //拼自付款金额
    private void formatData(List<FormProgressVO> formProgressVOs,List<HtDeductibleInfo> htDeductibleInfoList) {
        Map<String,BigDecimal> map = htDeductibleInfoList.stream().collect(Collectors.toMap(HtDeductibleInfo::getFormId,HtDeductibleInfo::getDeductiblesLimit));
        for(FormProgressVO formProgressVO : formProgressVOs){
            if(map.containsKey(formProgressVO.getFormId())){
                formProgressVO.setDeductiblesLimit(map.get(formProgressVO.getFormId()));
            }
        }
    }

    @GetMapping("getMaintenanceReport")
    @ApiOperation(value = "查询维修报告",notes = "查询维修报告")
    @ApiImplicitParam(name = "formId",value = "工单id",dataType="string", paramType = "query")
    public ApiResult<HtRepairClientForm> get(String formId){
        Map<String,String> params = new HashMap<>();
        params.put("formId",formId);
        List<HtRepairClientForm> htRepairClientForms = htRepairClientFormMapper.select(params);
        if(CollectionUtils.isEmpty(htRepairClientForms)){
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM,"维修报告为空");
        }
        return ApiResultFactory.getSuccess(htRepairClientForms.stream().findFirst().get());
    }


}
