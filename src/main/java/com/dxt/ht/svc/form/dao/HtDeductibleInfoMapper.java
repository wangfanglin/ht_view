package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.form.entity.HtDeductibleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 自付款信息Mapper
 *
 * @author wangfanglin
 * @date 2020/04/14
 */
@Mapper
@Repository
public interface HtDeductibleInfoMapper extends CommonDao {

    /**
     * 根据表主键id删除一行信息
     *
     * @param id 主键id
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     *
     * @param record 自付款信息对象
     * @return
     */
    int insert(HtDeductibleInfo record);

    void updateStatus(@Param("outId") String outId, @Param("status") String status);

    HtDeductibleInfo selectByPrimaryKey(@Param("id") String id);

    String selectPayStatus(@Param("fromId") String fromId);

    List<HtDeductibleInfo> selectByFormIds(List<String> formIds);
}