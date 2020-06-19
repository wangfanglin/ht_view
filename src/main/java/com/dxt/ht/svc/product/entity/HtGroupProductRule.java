package com.dxt.ht.svc.product.entity;

import lombok.Data;

import java.util.List;

/**
 * 组合产品规则
 *
 * @author wangfanglin
 * @date 2020/04/15
 */
@Data
public class HtGroupProductRule {

    /**组合产品子表对象集合*/
    private List<HtGroupProductChild> htGroupProductChildren;

    /**组合产品表队形*/
    private HtGroupProductInfo htGroupProductInfo;

    /**服务有效期显示内容*/
    private String serviceValidity;
}
