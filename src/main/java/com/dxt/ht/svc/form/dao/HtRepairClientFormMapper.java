package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/***
 * 维修工单-待联系客户数据库操作
 *
 * @author wangfanglin
 * @date 2020/04/10
 */
@Mapper
@Repository
public interface HtRepairClientFormMapper extends CommonDao {

    /**
     * 通过主键id 删除信息
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

}