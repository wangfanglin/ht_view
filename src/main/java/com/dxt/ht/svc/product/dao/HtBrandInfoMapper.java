package com.dxt.ht.svc.product.dao;

import com.dxt.ht.svc.product.entity.HtBrandInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HtBrandInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HtBrandInfo record);

    int insertSelective(HtBrandInfo record);

    HtBrandInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HtBrandInfo record);

    int updateByPrimaryKey(HtBrandInfo record);
}