package com.dxt.ht.svc.sf.service;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 顺丰相关数据查询
 * @author tangchao
 */
@Mapper
public interface SfOrderInfoService {
    /**
     * 顺丰接口调用日志添加
     * @param orderId   顺丰订单id
     * @param myReqXML  入参
     * @param respXml   出参
     */
    void insertSfLog(@Param("orderId") String orderId, @Param("myReqXML") String myReqXML, @Param("respXml") String respXml);


}
