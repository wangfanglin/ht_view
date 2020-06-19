package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.form.entity.HtAssemblyUnit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 维修部件Mapper接口
 *
 * @author wangfanglin
 * @date 2020/04/01
 */

@Mapper
@Repository
public interface HtAssemblyUnitMapper extends CommonDao {

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
    int insert(HtAssemblyUnit record);

    /**
     * insertSelective
     * @param record
     * @return int sql_affected_rows
     */
    int insertSelective(HtAssemblyUnit record);

    /**
     * selectByPrimaryKey
     * @param id
     * @return HtAssemblyUnit
     */
    HtAssemblyUnit selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return sql_affected_rows
     */
    int updateByPrimaryKeySelective(HtAssemblyUnit record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return sql_affected_rows
     */
    int updateByPrimaryKey(HtAssemblyUnit record);
}