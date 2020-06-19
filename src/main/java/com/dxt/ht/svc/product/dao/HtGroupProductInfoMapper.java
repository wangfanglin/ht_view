package com.dxt.ht.svc.product.dao;


import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.product.entity.HtGroupProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 组合产品表
 *
 * @author wangfanglin
 * @date 2020/04/15
 */
@Mapper
@Repository
public interface HtGroupProductInfoMapper extends CommonDao {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    HtGroupProductInfo selectByPrimaryKey(String id);

}