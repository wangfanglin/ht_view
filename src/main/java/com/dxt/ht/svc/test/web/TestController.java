package com.dxt.ht.svc.test.web;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.test.entity.TestBean;
import com.dxt.ht.svc.test.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "测试模块")
@RequestMapping("api/")
public class TestController {

@Autowired
    TestService testService;



    @ApiOperation(value = "测试方法", notes = "这是一个测试方法")
    @GetMapping("test")
    public ApiResult<List<TestBean>> test(@Valid @NotNull String name, @NotNull String phone){
        List<TestBean> list = new ArrayList<>();
        list.add(new TestBean("1","3"));
        list.add(new TestBean("2","4"));
        ApiResult<List<TestBean>> apiResult = ApiResultFactory.getSuccess(list);
        return apiResult;
    }

    @ApiOperation(value = "测试方法1", notes = "这是一个测试方法1")
    @GetMapping("test1")
    @ApiImplicitParam(name="phone",value="手机号",dataType="string", paramType = "query")
    public ApiResult<List<TestBean>> test1(String phone){
        List<TestBean> list = new ArrayList<>();
        list.add(new TestBean("1","3"));
        list.add(new TestBean("2","4"));
        ApiResult<List<TestBean>> apiResult = ApiResultFactory.getSuccess(list);
        return apiResult;
    }
    @ApiOperation(value = "测试获取城市", notes = "测试获取城市")
    @GetMapping("testArea")
    public ApiResult<List<Map<String,Object>>> testArea(){
        List<Map<String,Object>> list = testService.getAreaList();
        ApiResult<List<Map<String,Object>>> apiResult = ApiResultFactory.getSuccess(list);
        return apiResult;
    }

}
