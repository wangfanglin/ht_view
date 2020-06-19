package com.dxt.ht.svc.test.service;

import com.dxt.ht.svc.test.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    public List<Map<String,Object>> getAreaList(){
        return testDao.getArea();
    }


}
