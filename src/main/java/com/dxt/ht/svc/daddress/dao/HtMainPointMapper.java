package com.dxt.ht.svc.daddress.dao;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.daddress.entity.HtMainPoint;
import com.dxt.ht.svc.sf.entity.SfOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Mapper
@Repository
public interface HtMainPointMapper {
    /**
     * 查找一条维修网点地址
     * @param id 订单id
     * @return 一条维修网点的地址信息
     */
    HtMainPoint findPostAddress(String id);

    /**
     * 查找收件地址（维修和换新）
     * @return
     */
    ApiResult<HtMainPoint> findDaddress(String id);
    /**
     * 判断工单类型
     * @param id 订单id
     * @return
     */
    HtMainPoint findType(String id);

    /**
     * 创建换新地址列表
     * @param id
     * @return
     */
    HtMainPoint findChangdeAddress(String id);

}