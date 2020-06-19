package com.dxt.ht.svc.area.service;

import com.dxt.ht.svc.area.dao.JsSysAreaMapper;
import com.dxt.ht.svc.area.entity.JsSysArea;
import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags="地址模块")
@RequestMapping("api/address")
public class JsFindAreaService {
    @Autowired
    private JsSysAreaMapper jsSysAreaMapper;

    /**
     *查找省集合
     * @return省集合
     */
    @GetMapping("findProvinceList")
    @ApiOperation(value = "查找省集合", notes = "查找省集合方法")
    public ApiResult<List<JsSysArea>> findProvinceList() {
        List<JsSysArea> list = jsSysAreaMapper.findProvinceList();
        if (CollectionUtils.isEmpty(list)) {
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA);
        }
        return ApiResultFactory.getSuccess(list);
    }

    /**
     *根据省编码查找市集合
     * @param areaCode 省编码
     * @return 市集合
     */
    @GetMapping("findCityList")
    @ApiOperation(value = "查找市集合", notes = "查找市集合方法")
    @ApiImplicitParam(name = "areaCode", value = "区域编码", dataType = "string", paramType = "query")
    public ApiResult<List<JsSysArea>> findCityList(String areaCode) {
        if(StringUtils.isBlank(areaCode)){
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "无效的区域编码");

        }
        List<JsSysArea> list = jsSysAreaMapper.findCityList(areaCode);
        if (CollectionUtils.isEmpty(list)) {
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA);
        }
        return ApiResultFactory.getSuccess(list);
    }

    /**
     * 根据市编码查找区域集合
     * @param areaCode 市编码
     * @return 区域集合
     */
    @GetMapping("findAreaList")
    @ApiOperation(value = "查找区域集合", notes = "查找区域集合方法")
    @ApiImplicitParam(name = "areaCode", value = "区域编码", dataType = "string", paramType = "query")
    public ApiResult<List<JsSysArea>> findAreaList(String areaCode) {
        if(StringUtils.isBlank(areaCode)){
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "无效的区域编码");
        }
        List<JsSysArea> list = jsSysAreaMapper.findAreaList(areaCode);
        if (CollectionUtils.isEmpty(list)) {
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA);
        }
        return ApiResultFactory.getSuccess(list);
    }

}
