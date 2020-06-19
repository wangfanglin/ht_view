package com.dxt.ht.svc.daddress.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HtMainPointMapperTest {
    @Autowired
    HtMainPointMapper htMainPointMapper;


    @Test
    void findPostAddress() {
        htMainPointMapper.findPostAddress("1260835488045121536");
    }
}