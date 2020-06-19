package com.dxt.ht.svc.wx.log.dao;

import com.dxt.ht.svc.wx.log.entity.HtPayLog;
import com.dxt.ht.svc.wx.log.entity.HtPayLogWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HtPayLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HtPayLogWithBLOBs record);

    int insertSelective(HtPayLogWithBLOBs record);

    HtPayLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HtPayLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(HtPayLogWithBLOBs record);

    int updateByPrimaryKey(HtPayLog record);
}