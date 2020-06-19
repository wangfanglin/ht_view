package com.dxt.ht.svc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

class HtSvcApplicationTests {

    @Test
    void contextLoads() {

    }
    @Test
    public void get (){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        List<String> list1 = list.subList(0,2);
        List<String> list2 = new ArrayList<>();
        list2.addAll(list1);
        list.add("f");
        for(String s : list2){
            System.out.println(s);
        }

    }

    @Test
    public void get1(){
        String[] a = new String[]{"a","b","c","d","e"};
        List<String> list = Arrays.asList(a);
        List<String> list1 = new ArrayList<>();
        list1.addAll(list);
        a[0] = "f";
        list1.add("f");
        for(String s : list1){
            System.out.println(s);
        }
    }
    @Test
    public void get2(){
        List list = new ArrayList<>();
        List<String> list1 = new ArrayList();
        list.add(new String("a"));
        list.add(new String("a"));
        list.add(new Integer(1));
        list.forEach(e->{
            if(e instanceof String){
                list1.add(e.toString());
            }
        });
        for(String s : list1){
            System.out.println(s);
        }

    }
    @Test
    public void get3(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0);
        list.add(0);
        list.sort(Integer::compareTo);
        for(Integer i : list){
            System.out.println(i);
        }

    }
    @Test
    public void get4(){
        Map<String,String> map = new HashMap();
        map.put("a","A");
        map.put("b","B");
        map.put("c","C");
        map.forEach((k,v)->{
            System.out.print("key -> "+k);
            System.out.println(" values -> "+v);
        });
    }
}
