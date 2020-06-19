package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.form.entity.HtRepairOfferPart;
import com.dxt.ht.svc.form.entity.vo.HtRepairOfferPartVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 维修工单-待报价-损害部位Mapper接口
 *
 * @author wangfanglin
 * @date 2020/04/01
 */

@Mapper
@Repository
public interface HtRepairOfferPartMapper {

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
    int insert(HtRepairOfferPart record);

    /**
     * insertSelective
     * @param record
     * @return sql_affected_rows
     */
    int insertSelective(HtRepairOfferPart record);

    /**
     * selectByPrimaryKey
     * @param id
     * @return HtRepairOfferPart
     */
    HtRepairOfferPart selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return sql_affected_rows
     */
    int updateByPrimaryKeySelective(HtRepairOfferPart record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return sql_affected_rows
     */
    int updateByPrimaryKey(HtRepairOfferPart record);

    /**
     * selectByFormId
     * @param id
     * @return HtRepairOfferPartVO
     */
    List<HtRepairOfferPartVO> selectByFormId(String id);

}