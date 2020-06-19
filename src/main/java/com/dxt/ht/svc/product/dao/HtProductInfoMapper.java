package com.dxt.ht.svc.product.dao;

import com.dxt.ht.svc.form.entity.HtFormInfo;
import com.dxt.ht.svc.product.entity.HtProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HtProductInfoMapper {
    /**
     * 根据产品id获取信息
     *
     * @param productIds
     * @return
     */
    List<HtProductInfo> selectByProductIds (List<String> productIds);
}