package com.dxt.ht.svc.user.dao;

import com.dxt.ht.svc.common.utils.CommonDao;
import com.dxt.ht.svc.user.entity.HtUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HtUserInfoDao extends CommonDao {

    void saveUser(HtUserInfo htUserInfo);

    HtUserInfo getUserInfo(String userId);
}
