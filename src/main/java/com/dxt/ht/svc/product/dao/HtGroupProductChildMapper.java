package com.dxt.ht.svc.product.dao;


import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.product.entity.HtGroupProductChild;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组合产品子表
 *
 * @author wangfanglin
 * @date 2020/04/15
 */
@Mapper
@Repository
public interface HtGroupProductChildMapper extends CommonDao {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    HtGroupProductChild selectByPrimaryKey(String id);

}