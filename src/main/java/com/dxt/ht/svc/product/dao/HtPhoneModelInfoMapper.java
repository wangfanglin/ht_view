package com.dxt.ht.svc.product.dao;

import com.dxt.ht.svc.product.entity.HtPhoneModelInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HtPhoneModelInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(HtPhoneModelInfo record);

    int insertSelective(HtPhoneModelInfo record);

    HtPhoneModelInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HtPhoneModelInfo record);

    int updateByPrimaryKey(HtPhoneModelInfo record);
}