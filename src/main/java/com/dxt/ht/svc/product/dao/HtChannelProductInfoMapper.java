package com.dxt.ht.svc.product.dao;


import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.product.entity.HtChannelProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 渠道产品表
 *
 * @author wangfanglin
 * @date 2020/04/15
 */
@Mapper
@Repository
public interface HtChannelProductInfoMapper extends CommonDao {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    HtChannelProductInfo selectByPrimaryKey(String id);

}