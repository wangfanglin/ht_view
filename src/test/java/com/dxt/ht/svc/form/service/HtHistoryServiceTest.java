package com.dxt.ht.svc.form.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class HtHistoryServiceTest {

    @Autowired
    HtHistoryService htHistoryService;

    @Test
    void getFormRateProgress() {
        htHistoryService.getFormRateProgress("3816645587233017276");
    }

    @Test
    void get() {
        List<String> list = new ArrayList<>(2);
        list.add("1");
        list.add("2");
        for(String s : list){
            if("2".equals(s)){
                list.remove(s);
            }
            System.out.println(list);
        }
        System.out.println("============================================");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            if("2".equals(s)){
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}