package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.form.entity.HtReceiptData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HtReceiptDataMapper extends CommonDao {
    int deleteByPrimaryKey(String id);

    int insert(HtReceiptData record);

    int insertSelective(HtReceiptData record);

    HtReceiptData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HtReceiptData record);

    int updateByFormId(HtReceiptData record);

    int updateByPrimaryKey(HtReceiptData record);

    String selectReceiptStatus(String fromId);

    String selectReceiptAuditStatus(String fromId);
}