package com.dxt.ht.svc.policy.dao;

import com.dxt.ht.svc.policy.entity.HtPolicyDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HtPolicyDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HtPolicyDetail record);

    int insertSelective(HtPolicyDetail record);

    HtPolicyDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HtPolicyDetail record);

    int updateByPrimaryKey(HtPolicyDetail record);
}