package com.dxt.ht.svc.jaddress.service;

import com.dxt.ht.svc.jaddress.dao.HtUserAddressDao;
import com.dxt.ht.svc.jaddress.entity.HtUserAddress;
import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags="寄件地址模块")
@RequestMapping("api/jaddress")

public class HtUserAddressService {
    @Autowired
    private HtUserAddressDao htUserAddressDao;


    /**
     * 查找地址信息列表
     * @param userId 用户id
     * @return 地址列表
     */
    @GetMapping("findUserAddress")
    @ApiOperation(value = "查询地址列表", notes = "查询地址列表方法")
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "string", paramType = "query")
    public ApiResult<List<HtUserAddress>> findUserAddress(String userId) {
        if (StringUtils.isBlank(userId)) {
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "无效的用户id");
        }
        List<HtUserAddress> list = htUserAddressDao.findUserAddress(userId);
        if (CollectionUtils.isEmpty(list)) {
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA);
        }
        return ApiResultFactory.getSuccess(list);
    }

    /**
     * 查找单条地址信息
     * @param id 地址id
     * @param userId 用户id
     * @return 单条地址信息
     */
    @GetMapping("singleUserAddress")
    @ApiOperation(value = "查询单条地址", notes = "查询单条地址方法")
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "string", paramType = "query")
    public ApiResult<HtUserAddress> singleUserAddress(Integer id,String userId) {
        if (id == null) {
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "无效的地址id");
        }
        if (StringUtils.isBlank(userId)) {
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "无效的用户id");
        }
        HtUserAddress htUserAddressInfo = htUserAddressDao.singleUserAddress(id,userId);
        if (htUserAddressInfo == null ) {
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA);

        }
        StrBuilder strBuilderJaddress = new StrBuilder();
        strBuilderJaddress.append(htUserAddressInfo.getProvinceCode()).append(htUserAddressInfo.getCityCode()).append(htUserAddressInfo.getAreaCode()).append(htUserAddressInfo.getAddress());
        htUserAddressInfo.setAddress(strBuilderJaddress.toString());
        return ApiResultFactory.getSuccess(htUserAddressInfo);
    }

    /**
     * 增加地址信息
     * @param htUserAddress 每条地址信息的所有内容
     */
    @PostMapping("saveUserAddress")
    @ApiOperation(value = "新增地址", notes = "新增地址方法")
    public ApiResult saveUserInfo(@RequestBody @Valid HtUserAddress htUserAddress) {
        int result=htUserAddressDao.saveUserAddress(htUserAddress);
        if(result == 0){
            return ApiResultFactory.newInstance(ResultStatus.NONE_CHANGE,"增加无效");
        }
        return ApiResultFactory.getSuccess();

    }
    /**
     * 删除地址信息
     * @param id 地址id
     * @param userId 用户id
     * @return 操作是否成功
     */

    @PostMapping("delUserAddress")
    @ApiOperation(value = "删除地址", notes = "删除地址方法")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "id", value = "地址id", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "string", paramType = "query")
    }
    )
    public ApiResult delUserAddress(@RequestBody  HtUserAddress htUserAddress) {
        Integer id = htUserAddress.getId();
        String userId = htUserAddress.getUserId();
        if (id == null) {
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "无效的地址id");
        }
        if (StringUtils.isBlank(userId)) {
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "无效的用户id");
        }
        int result =htUserAddressDao.delUserAddress(id,userId);
        if(result == 0){
            return ApiResultFactory.newInstance(ResultStatus.NONE_CHANGE,"删除无效");
        }
        return ApiResultFactory.getSuccess();
    }

    /**
     * 修改地址信息
     * @param htUserAddress 单条地址信息
     */
    @PutMapping("updateUserAddress")
    @ApiOperation(value = "修改地址", notes = "修改地址方法")
    public ApiResult<ResultStatus> updateUserAddress(@RequestBody @Valid HtUserAddress htUserAddress) {
        int result=htUserAddressDao.updateUserAddress(htUserAddress);
        if(result == 0){
            return ApiResultFactory.newInstance(ResultStatus.NONE_CHANGE,"修改无效");
        }
        return ApiResultFactory.getSuccess();


    }


}



















