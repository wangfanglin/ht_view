package com.dxt.ht.svc.template.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.template.entity.WxTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WxTemplateMapper extends CommonDao {

    int insert(WxTemplate record);

    int insertSelective(WxTemplate record);

    List<WxTemplate> findByTemplateType(WxTemplate wxTemplate);
}