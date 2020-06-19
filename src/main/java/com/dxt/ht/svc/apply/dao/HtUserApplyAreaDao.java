package com.dxt.ht.svc.apply.dao;

import com.dxt.ht.svc.apply.entity.HtUserApplyArea;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HtUserApplyAreaDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HtUserApplyArea record);

    int insertSelective(HtUserApplyArea record);

    HtUserApplyArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HtUserApplyArea record);

    int updateByPrimaryKey(HtUserApplyArea record);
}