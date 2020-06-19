package com.dxt.ht.svc.sf.dao;

import com.dxt.ht.svc.sf.entity.SfOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SfOrderDao {
    /**
     * 更新顺丰订单
     * @param sfOrderInfo 每条顺丰订单的信息
     * @return 操作成功条数
     */
    int updateByPrimaryKeySelective(SfOrderInfo sfOrderInfo);
    /**
     * 查找收寄地址
     * @param mailNo 顺丰单号
     * @return
     */
    SfOrderInfo findAddress(String mailNo);


    /**
     * 查询顺丰物流
     * @param orderId 订单id
     * @return 物流信息
     */
    SfOrderInfo logisticsInformation(String orderId);
    //String getMail(String orderId);



}
