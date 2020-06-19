package com.dxt.ht.svc.common.result;

/**
 *  返回信息接口
 *
 *  此接口说明了返回的要素为
 *
 *  状态 和 说明
 *
 */
public interface Result {
    /**
     * 获取返回的状态
     * @return
     */
    String getStatus();

    /**
     * 获取返回的说明
     * @return
     */
    String getContent();
}
