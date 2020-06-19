package com.dxt.ht.svc.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestDao {


    List<Map<String,Object>> getArea();
}
