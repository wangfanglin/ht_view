package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.form.entity.HtClaimDisqualificationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *  核赔不合格日志表
 *
 * @author wangfanglin
 * @date 2020/04/07
 */
@Mapper
@Repository
public interface HtClaimDisqualificationLogMapper {

    /**
     * deleteByPrimaryKey
     * @param id
     * @return int sql_affected_rows
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert
     * @param record
     * @return int sql_affected_rows
     */
    int insert(HtClaimDisqualificationLog record);

    /**
     * insertSelective
     * @param record
     * @return int sql_affected_rows
     */
    int insertSelective(HtClaimDisqualificationLog record);

    /**
     * insertSelective
     * @param id
     * @return HtClaimDisqualificationLog
     */
    HtClaimDisqualificationLog selectByPrimaryKey(String id);

    /**
     * insertSelective
     * @param record
     * @return int sql_affected_rows
     */
    int updateByPrimaryKeySelective(HtClaimDisqualificationLog record);

    /**
     * insertSelective
     * @param record
     * @return int sql_affected_rows
     */
    int updateByPrimaryKey(HtClaimDisqualificationLog record);

    /**
     * selectByFormId 根据工单id查询
     * @param id
     * @return HtClaimDisqualificationLog
     */
    HtClaimDisqualificationLog selectByFormId(String id);
}