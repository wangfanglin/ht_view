package com.dxt.ht.svc.policy.service;

import com.dxt.ht.svc.policy.dao.HtPolicyInfoMapper;
import com.dxt.ht.svc.policy.entity.HtPolicyInfo;
import com.dxt.ht.svc.policy.entity.PolicyInfoListEntity;
import com.dxt.ht.svc.wx.WeixinUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

@SpringBootTest
class HtPolicyInfoServiceTest{

    @Autowired
    HtPolicyInfoMapper htPolicyInfoMapper;

    @Test
    void getPolicyInfoList() {
        PolicyInfoListEntity policyInfoListEntity = new PolicyInfoListEntity();
        policyInfoListEntity.setQuertType("2");
        policyInfoListEntity.setQueryParam("15510787179");
        List<HtPolicyInfo> policyInfoList = htPolicyInfoMapper.findPolicyList(policyInfoListEntity);
        System.out.println(policyInfoList);
    }
    @Test
    void get1(){
        String a = "33_71mafCxOsxZN1IgphcnC7GFMLx0_vpgHedqPwPEzgjuagGUl7W_yFIbGyusu7HB0uiKtZL4weZ5wnWXlGrhU5FsEZbdAVIpmlaEUr4kJXMo";
        String b = "oJPBx5sCmwAyv1IcqloiG4_sI1Ls";
        try {
           Map map = WeixinUtil.getWeixinUserInfo(b, a);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}