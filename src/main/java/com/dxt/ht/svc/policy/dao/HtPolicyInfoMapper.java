package com.dxt.ht.svc.policy.dao;

import com.dxt.ht.svc.policy.entity.HtPolicyInfo;
import com.dxt.ht.svc.policy.entity.PolicyInfoListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HtPolicyInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HtPolicyInfo record);

    int insertSelective(HtPolicyInfo record);

    HtPolicyInfo selectByPrimaryKey(String policyId);

    int updateByPrimaryKeySelective(HtPolicyInfo record);

    int updateByPrimaryKey(HtPolicyInfo record);

    List<HtPolicyInfo> findPolicyList(PolicyInfoListEntity policyInfoListEntity);

    HtPolicyInfo selectByFormId(String formId);

    HtPolicyInfo selectReceiptByFormId(String formId);

}