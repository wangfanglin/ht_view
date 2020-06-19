package com.dxt.ht.svc.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class TestBean {
    @ApiModelProperty(value = "姓名",required=true)
    @NotNull(message = "名字不能为空")
    private String name;
    @ApiModelProperty(value = "手机",required=true)
    @NotNull(message = "手机不能为空")
    private String phone;

    public TestBean(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
