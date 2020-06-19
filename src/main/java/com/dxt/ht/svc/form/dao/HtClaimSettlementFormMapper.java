package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 在线理赔信息表
 *
 * @author wangfanglin
 * @date 2020/04/10
 */
@Mapper
@Repository
public interface HtClaimSettlementFormMapper extends CommonDao {

    int findExpressCount(@Param("formId") String formId);

}