package com.dxt.ht.svc.apply.dao;

import com.dxt.ht.svc.apply.entity.HtUserApplyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HtUserApplyInfoDao {
    int deleteByPrimaryKey(String id);

    int insert(HtUserApplyInfo htUserApplyInfo);

    int insertSelective(HtUserApplyInfo record);

    HtUserApplyInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HtUserApplyInfo record);

    int updateByPrimaryKeyWithBLOBs(HtUserApplyInfo record);

    int updateByPrimaryKey(HtUserApplyInfo record);

    HtUserApplyInfo selectInfoByFormId(String formId);

    HtUserApplyInfo getApplyStatus(@Param("policyId") String policyId);
}