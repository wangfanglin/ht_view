package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.form.entity.HtFormInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HtFormInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HtFormInfo record);

    int insertSelective(HtFormInfo record);

    HtFormInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HtFormInfo record);

    int updateByPrimaryKey(HtFormInfo record);

    List<HtFormInfo> findAllFrom(@Param("policyId") String policyId);
}