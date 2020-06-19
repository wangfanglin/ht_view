package com.dxt.ht.svc.form.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.form.dao.JsSysDictDataMapper;
import com.dxt.ht.svc.form.entity.JsSysDictData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "字典模块")
@RequestMapping(value = "api/js/sys/dict/data")
public class JsSysDictDataService {

    @Autowired
    private JsSysDictDataMapper jsSysDictDataMapper;

    @GetMapping(value = "getJsSysDictDataByDictType")
    @ApiOperation(value = "获取损坏原因",notes = "获取损坏原因")
    public ApiResult<List<JsSysDictData>> getJsSysDictData(){
        Map<String,String> params = new HashMap<>();
        params.put("dictType","damage_reason");
        List<JsSysDictData> dictList = jsSysDictDataMapper.select(params);
        if(CollectionUtils.isEmpty(dictList)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"数据为空，请检查参数是否传递错误");
        }
        return ApiResultFactory.getSuccess(dictList);
    }
}
