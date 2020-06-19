package com.dxt.ht.svc.apply.service;

import com.dxt.ht.svc.apply.entity.HtUserApplyInfo;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class HtUserApplyInfoServiceTest {

    @Autowired
    HtUserApplyInfoService htUserApplyInfoService;

    @Test
    public void get(){
        htUserApplyInfoService.formatFormIdData("1265842230168420352");
    }

    @Test
    public void get1() {
        List<String> list = new ArrayList<>(2);
        list.add("1");
        list.add("2");
        list.forEach(e->{
            if("2".equals(e)){
                list.remove(e);
            }
        });
        System.out.println("foreach遍历中操作remove"+list);
        /*List<String> list1 = new ArrayList<>(2);
        list1.add("1");
        list1.add("2");
        for(String s : list1){
            if("1".equals(s)){
                list1.add(s);
            }
        }
        System.out.println("foreach遍历中操作add"+list1);*/
    }

}