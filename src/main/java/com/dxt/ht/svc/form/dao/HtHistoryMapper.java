package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.form.entity.HtHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HtHistoryMapper extends CommonDao {
    int deleteByPrimaryKey(String id);

    int insert(HtHistory record);

    int insertSelective(HtHistory record);

    HtHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HtHistory record);

    int updateByPrimaryKey(HtHistory record);

    List<HtHistory> findHistList(@Param("formId") String formId);

    String selectFormOperation(String uniquenessId);

    Map<String, Object> getRepairClient(String historyFormId);

    String selectHtRenewHistoryById(String historyId);

    int updateOperationStatusByPrimaryKeySelective(HtHistory record);

}