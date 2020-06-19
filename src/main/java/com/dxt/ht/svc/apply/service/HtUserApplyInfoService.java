package com.dxt.ht.svc.apply.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dxt.ht.svc.apply.entity.AssessList;
import com.dxt.ht.svc.apply.entity.ReportCaseParameter;
import com.dxt.ht.svc.bohai.Utils;
import com.dxt.ht.svc.common.utils.DateUtil;
import com.dxt.ht.svc.common.utils.HttpReqUtils;
import com.dxt.ht.svc.form.dao.HtHistoryMapper;
import com.dxt.ht.svc.form.entity.HtHistory;
import com.dxt.ht.svc.form.entity.vo.FormProgressVO;
import com.dxt.ht.svc.jaddress.dao.HtUserAddressDao;
import com.dxt.ht.svc.jaddress.entity.HtUserAddress;
import com.dxt.ht.svc.apply.dao.HtUserApplyAreaDao;
import com.dxt.ht.svc.apply.dao.HtUserApplyInfoDao;
import com.dxt.ht.svc.apply.entity.HtUserApplyArea;
import com.dxt.ht.svc.apply.entity.HtUserApplyInfo;
import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.common.utils.ID;
import com.dxt.ht.svc.policy.dao.HtPolicyInfoMapper;
import com.dxt.ht.svc.policy.entity.HtPolicyInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Api(tags = "保单模块")
@RequestMapping("api/htUserApplyInfo")
public class HtUserApplyInfoService {

    @Autowired
    HtUserApplyInfoDao htUserApplyInfoDao;
    @Autowired
    HtUserApplyAreaDao htUserApplyAreaDao;
    @Autowired
    HtPolicyInfoMapper htPolicyInfoMapper;
    @Autowired
    HtUserAddressDao htUserAddressDao;
    @Autowired
    HtHistoryMapper htHistoryMapper;

    private static final String CHANNEL_ID = "81";

    /**资料不合格待联系*/
    private static final String ZLBHG_DLX = "1009";

    /**重新提交理赔资料*/
    private static final String CXTJLPZL = "1010";

    /**
     * 新增在线理赔信息
     * @param htUserApplyInfo
     * @return ApiResult<ResultStatus>
     */
    @PostMapping("saveApplyInfo")
    @ApiOperation(value = "新增在线理赔信息", notes = "新增在线理赔信息")
    public ApiResult<ResultStatus> saveApplyInfo(@RequestBody @Valid HtUserApplyInfo htUserApplyInfo){
        String policy = htUserApplyInfo.getPolicyId();
        String addressId = htUserApplyInfo.getAreaId();
        HtPolicyInfo htPolicyInfo = htPolicyInfoMapper.selectByPrimaryKey(policy);
        //通过保单ID获取保单信息
        initPolicyInfo(htUserApplyInfo, htPolicyInfo);
        HtUserAddress userAddress = htUserAddressDao.findUserAddressInfo(Integer.parseInt(addressId), htUserApplyInfo.getUserId());
        HtUserApplyArea addArea = new HtUserApplyArea();
        BeanUtils.copyProperties(userAddress,addArea);
        addArea.setId(null);
        //如果该申请的渠道为捷信渠道 调用渤海报案接口
        try {
            if(CHANNEL_ID.equals(htPolicyInfo.getChannelId())) {
                bohaiReprotSubmit(htUserApplyInfo,userAddress,htPolicyInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        htUserApplyAreaDao.insertSelective(addArea);
        htUserApplyInfo.setId(ID.getId());
        htUserApplyInfo.setAreaId(addArea.getId().toString());
        htUserApplyInfo.setIsMain("1");
        htUserApplyInfoDao.insertSelective(htUserApplyInfo);
        return ApiResultFactory.getSuccess();
    }

    /**
     * String deviceCode, String linkName, String rptDate, String city, String district, String tel, String idCard, String deviceType, String deviceModel, String deviceAttr, String accdntSubcaus, List<AssessList> assessList, String sugAllAmt, String systemId
     * 渤海报案接口
     * @param htUserApplyInfo
     */
    private boolean bohaiReprotSubmit(HtUserApplyInfo htUserApplyInfo,HtUserAddress userAddress,HtPolicyInfo htPolicyInfo) {
        try {

            AssessList assessList = new AssessList("4", "14", "15", "280");
            AssessList assessList1 = new AssessList("3", "11", "14", "800");
            AssessList assessList2 = new AssessList("3", "13", "13", "300");
            List<AssessList> assessLists = new ArrayList<>();
            assessLists.add(assessList);
            assessLists.add(assessList1);
            assessLists.add(assessList2);
            ReportCaseParameter reportCaseParameter = new ReportCaseParameter(userAddress.getProvinceCode(),htPolicyInfo.getStrImeiNum(), userAddress.getUserName(), DateUtil.nowTime(),
                    userAddress.getCityCode(), userAddress.getAreaCode(),userAddress.getUserPhone(), htUserApplyInfo.getCardId(),  "1", htPolicyInfo.getHtPhoneModelInfo().getModel(),"红色", "0050021", assessLists, "1380", "HD");
            /*ReportCaseParameter reportCaseParameter = new ReportCaseParameter("867911044582757", "xxx", DateUtil.nowTime(),
                    userAddress.getCityCode(),userAddress.getAreaCode(),"12345678912", "422455199602051239",  "1", "11","红色", "0050021", assessLists, "1380", "HD");*/
            reportCaseParameter.setRemark("");
            reportCaseParameter.setAccdntDtl("损坏");
            reportCaseParameter.setReplmgSerNo("e74ae6c2bfc7444981075017fec8348b");
            reportCaseParameter.setVisit("2020-05-17 00:00:00");
            reportCaseParameter.setRptCde("112049911");
            reportCaseParameter.setKindCode("0020001");
            reportCaseParameter.setRepair("");
            reportCaseParameter.setAccdntCaus("0150004");
            reportCaseParameter.setChannelCode("12690701");
            reportCaseParameter.setAllAmt("1380");
            reportCaseParameter.setIsJKXAdvancedFlag("0");
            reportCaseParameter.setRptWay("4000006");
            reportCaseParameter.setAddress(userAddress.getAddress());
            reportCaseParameter.setDeviceBrand("5");
            Map<String,Object> result = Utils.reprotSubmit(reportCaseParameter);
            if(!Utils.checkReprotSubmitResult(result)){
                return false;
            }
            //理赔案件号
            String clmNo = result.get("clmNo").toString();
            if (StringUtils.isEmpty(clmNo)) {
                return false;
            }

            htUserApplyInfo.setClmNo(clmNo);
            //凭证照片
            if(!StringUtils.isEmpty(htUserApplyInfo.getVoucherImg())){
                Utils.uploadImage(htUserApplyInfo.getVoucherImg(),clmNo);
            }
            //侧面照
            if(!StringUtils.isEmpty(htUserApplyInfo.getBadSideWimg())){
                Utils.uploadImage(htUserApplyInfo.getBadSideWimg(),clmNo);
            }
            //侧面照
            if(!StringUtils.isEmpty(htUserApplyInfo.getBadSideOimg())){
                Utils.uploadImage(htUserApplyInfo.getBadSideOimg(),clmNo);
            }
            //损坏反面照
            if(!StringUtils.isEmpty(htUserApplyInfo.getBadReverseImg())){
                Utils.uploadImage(htUserApplyInfo.getBadReverseImg(),clmNo);
            }
            //损坏正面照
            if(!StringUtils.isEmpty(htUserApplyInfo.getBadFrontImg())){
                Utils.uploadImage(htUserApplyInfo.getBadFrontImg(),clmNo);
            }
            //身份证反面照
            if(!StringUtils.isEmpty(htUserApplyInfo.getCardReverseImg())){
                Utils.uploadImage(htUserApplyInfo.getCardReverseImg(),clmNo);
            }
            //身份证正面照
            if(!StringUtils.isEmpty(htUserApplyInfo.getCardFrontImg())){
                Utils.uploadImage(htUserApplyInfo.getCardFrontImg(),clmNo);
            }
            //身份证手持照
            if(!StringUtils.isEmpty(htUserApplyInfo.getCardHandImg())){
                Utils.uploadImage(htUserApplyInfo.getCardHandImg(),clmNo);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 初始化保单信息
     * @param htUserApplyInfo
     * @param HtPolicyInfo
     */
    private void initPolicyInfo(HtUserApplyInfo htUserApplyInfo, HtPolicyInfo htPolicyInfo) {
        htUserApplyInfo.setFacilityBrandId(htPolicyInfo.getStrPhoneBrand());
        htUserApplyInfo.setFacilityColor(htPolicyInfo.getStrColor());
        htUserApplyInfo.setFacilityModelId(htPolicyInfo.getStrPhoneModel());
        htUserApplyInfo.setProductName(htPolicyInfo.getChannelProductId());
        htUserApplyInfo.setInsuranceFacilityName(htUserApplyInfo.getInsuranceFacilityName());
    }

    @PutMapping("updateApplyInfo")
    @ApiOperation(value = "修改在线理赔信息", notes = "修改在线理赔信息")
    public ApiResult<ResultStatus> updateApplyInfo(@RequestBody @Valid HtUserApplyInfo htUserApplyInfo){
        String policy = htUserApplyInfo.getPolicyId();
        HtPolicyInfo htPolicyInfo = htPolicyInfoMapper.selectByPrimaryKey(policy);
        initPolicyInfo(htUserApplyInfo, htPolicyInfo);
        if (StringUtils.isNotBlank(htUserApplyInfo.getAreaId())){
            HtUserAddress userAddress = htUserAddressDao.findUserAddressInfo(Integer.parseInt(htUserApplyInfo.getAreaId()),  htUserApplyInfo.getUserId());
            HtUserApplyArea addArea = new HtUserApplyArea();
            BeanUtils.copyProperties(userAddress,addArea);
            addArea.setId(null);
            htUserApplyAreaDao.insertSelective(addArea);
            htUserApplyInfo.setAreaId(addArea.getId().toString());
        }
        htUserApplyInfoDao.updateByPrimaryKeySelective(htUserApplyInfo);
        //当后台驳回时重新填写理赔信息并将这条工单历史进度的工单状态改变
        if(!StringUtils.isEmpty(htUserApplyInfo.getFormId())) {
            formatFormIdData(htUserApplyInfo.getFormId());
        }
        return ApiResultFactory.getSuccess();
    }

    /**拼装工单历史进度*/
    public void formatFormIdData(String formId) {
        List<HtHistory> histList = htHistoryMapper.findHistList(formId);
        Optional<HtHistory> findFirst = histList.stream().sorted(Comparator.comparing(HtHistory::getCreateDate).reversed()).filter(e->ZLBHG_DLX.equals(e.getOperationStatus())).findFirst();
        if(!findFirst.isPresent()){
            throw new NullPointerException();
        }
        HtHistory history = findFirst.get();
        HtHistory htHistory = new HtHistory();
        htHistory.setId(history.getId());
        htHistory.setOperationStatus(CXTJLPZL);
        htHistoryMapper.updateOperationStatusByPrimaryKeySelective(htHistory);
    }

    @GetMapping("findApplyInfo")
    @ApiOperation(value = "获取在线申请信息",notes = "获取在线申请信息")
    @ApiImplicitParam(name = "formId",value = "工单id",dataType="string", paramType = "query")
    public ApiResult<HtUserApplyInfo> updateApplyInfo(String formId){
        if (StringUtils.isBlank(formId)){
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM);
        }
        HtUserApplyInfo htUserApplyInfo = htUserApplyInfoDao.selectInfoByFormId(formId);
        if (htUserApplyInfo==null){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA);
        }
        return ApiResultFactory.getSuccess(htUserApplyInfo);
    }

    @GetMapping("findApplyArea")
    @ApiOperation(value = "获取申请地址",notes = "获取申请地址")
    @ApiImplicitParam(name = "id",value = "id",dataType="string", paramType = "query")
    public ApiResult<HtUserApplyArea> findApplyArea(String id){
        if (StringUtils.isBlank(id)){
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM);
        }
        HtUserApplyArea htUserApplyArea = htUserApplyAreaDao.selectByPrimaryKey(Integer.parseInt(id));
        if (htUserApplyArea==null){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA);
        }
        return ApiResultFactory.getSuccess(htUserApplyArea);
    }

}
