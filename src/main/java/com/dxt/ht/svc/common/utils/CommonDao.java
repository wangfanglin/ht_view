package com.dxt.ht.svc.common.utils;

import java.util.List;
import java.util.Map;

/**
 * 公共dao方法
 *
 * @author wangfanglin
 * @date 2020/04/08
 */
public interface CommonDao<T, E> {

    /**
     * 公共插入方法
     *
     * @param t
     */
    void insert(T t);

    /**
     * 公共单个删除方法
     *
     * @param id
     */
    void delById(String id);

    /**
     * 公共批量删除方法
     *
     * @param ids
     */
    void delByIds(List<String> ids);

    /**
     * 按照条件删除方法
     *
     * @param map
     */
    void del(Map map);

    /**
     * 通过主键删除方法
     *
     * @param t
     */
    void delByPrimaryKey(T t);

    /**
     * 更新方法id
     *
     * @param t
     */
    void updateById(T t);

    /**
     * 更新方法Map
     *
     * @param map
     */
    void update(Map<String, Object> map);

    /**
     * 更新方法主键
     *
     * @param t
     */
    void updateByPrimaryKey(T t);

    /**
     * 查询方法id
     *
     * @param id
     * @return E
     */
    E selectById(String id);

    /**
     * 批量查询方法 ids
     *
     * @param ids
     * @return List<E>
     */
    List<E> selectByIds(List<String> ids);

    /**
     * 查询方法Map
     *
     * @param conditionMap
     * @return
     */
    public List<E> select(Map<String, Object> conditionMap);

    /**
     * 统计方法
     *
     * @param conditionMap
     * @return
     */
    public Integer count(Map<String, Object> conditionMap);
    
}
