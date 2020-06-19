package com.dxt.ht.svc.form.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.form.entity.JsSysDictData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JsSysDictDataMapper extends CommonDao {

    int deleteByPrimaryKey(String dictCode);

    int insert(JsSysDictData record);

    int insertSelective(JsSysDictData record);

    JsSysDictData selectByPrimaryKey(String dictCode);

    int updateByPrimaryKeySelective(JsSysDictData record);

    int updateByPrimaryKey(JsSysDictData record);
}