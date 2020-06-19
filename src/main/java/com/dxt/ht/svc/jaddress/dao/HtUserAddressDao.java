package com.dxt.ht.svc.jaddress.dao;

import com.dxt.ht.svc.jaddress.entity.HtUserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HtUserAddressDao {

    /**
     * 查找地址信息列表
     * @param userId 用户id
     * @return 地址列表
     */
    List<HtUserAddress> findUserAddress(String userId);

    /**
     * 查找单条地址信息
     * @param id 地址id
     * @param userId 用户id
     * @return 单条地址信息
     */
    HtUserAddress singleUserAddress(Integer id,String userId);

    HtUserAddress findUserAddressInfo(@Param("id") Integer id,@Param("userId") String userId);
    /**
     * 增加地址信息
     * @param htUserAddress 每条地址信息的所有内容
     * @return 操作成功条数
     */
    int saveUserAddress(HtUserAddress htUserAddress);

    /**
     * 删除地址信息
     * @param id 地址id
     * @param userId 用户id
     * @return 操作成功条数
     */
    int delUserAddress(Integer id,String userId);

    /**
     * 修改地址信息
     * @param htUserAddress 每条地址信息的所有内容
     * @return 操作成功条数
     */
    int updateUserAddress(HtUserAddress htUserAddress);
}
