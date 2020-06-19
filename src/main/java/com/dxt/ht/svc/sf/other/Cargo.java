package com.dxt.ht.svc.sf.other;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Cargo{
    @XStreamAsAttribute
    private String name;        //货物名称,如果需要生成电子面单,则为必填。
    @XStreamAsAttribute
    private Integer count;      //货物数量跨境件报关需要填写


    public Cargo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}