package com.dxt.ht.svc.area.dao;

import com.dxt.ht.svc.area.entity.JsSysArea;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JsSysAreaMapper {

    /**
     *查找省集合
     * @return省集合
     */
    List<JsSysArea> findProvinceList();

    /**
     *根据省编码查找市集合
     * @param areaCode 省编码
     * @return 市集合
     */
    List<JsSysArea> findCityList(String areaCode);

    /**
     * 根据市编码查找区域集合
     * @param areaCode 市编码
     * @return 区域集合
     */
    List<JsSysArea> findAreaList(String areaCode);



}