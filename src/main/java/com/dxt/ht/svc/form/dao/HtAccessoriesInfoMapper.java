package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.form.entity.HtAccessoriesInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 配件Mapper接口
 *
 * @author wangfanglin
 * @date 2020/04/01
 */
@Mapper
@Repository
public interface HtAccessoriesInfoMapper {

    /**
     * deleteByPrimaryKey
     * @param id
     * @return sql_affected_rows
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert
     * @param record
     * @return sql_affected_rows
     */
    int insert(HtAccessoriesInfo record);

    /**
     * insertSelective
     * @param record
     * @return sql_affected_rows
     */
    int insertSelective(HtAccessoriesInfo record);

    /**
     * selectByPrimaryKey
     * @param id
     * @return HtAccessoriesInfo
     */
    HtAccessoriesInfo selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return sql_affected_rows
     */
    int updateByPrimaryKeySelective(HtAccessoriesInfo record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return sql_affected_rows
     */
    int updateByPrimaryKey(HtAccessoriesInfo record);
}