package com.dxt.ht.svc.user.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.common.utils.ID;
import com.dxt.ht.svc.user.dao.HtUserInfoDao;
import com.dxt.ht.svc.user.entity.HtUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Api(tags = "用户模块")
@RequestMapping("api/htUserInfo")
public class HtUserInfoService {

    @Autowired
    private HtUserInfoDao htUserInfoDao;

    /**
     * 新增用户信息
     * @param htUserInfo
     * @return
     */
    @PostMapping("saveUserInfo")
    @ApiOperation(value = "新增用户", notes = "新增用户方法")
    public ApiResult saveUserInfo(@Valid HtUserInfo htUserInfo){
        htUserInfo.setId(ID.getId());
        htUserInfoDao.saveUser(htUserInfo);
        return ApiResultFactory.getSuccess();
    }

    /**
     *获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("findUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParam(name="userId",value="用户id",dataType="string", paramType = "query")
    public ApiResult<HtUserInfo> findUserInfo(String userId){
        if(StringUtils.isEmpty(userId)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"用户id不存在，请检查后重新查询");
        }
        HtUserInfo htUserInfo = htUserInfoDao.getUserInfo(userId);
        if(ObjectUtils.isEmpty(htUserInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"用户id为 : "+userId+" 用户信息为空，或用户不存在!");
        }
        return ApiResultFactory.getSuccess(htUserInfo);
    }

    /**
     * 根据openId 保存用户信息 如果存在则做更新 反之则插入
     * @param htUser 用户
     * @return
     */
    @PostMapping("saveUser")
    @ApiOperation(value = "保存修改用户",notes = "保存修改用户")
    public ApiResult<HtUserInfo> saveUser(@RequestBody HtUserInfo htUser){

        Map<String,String> params = new HashMap<>();
        params.put("openId",htUser.getOpenId());
        List<HtUserInfo> htUserInfolist =  htUserInfoDao.select(params);
        if(CollectionUtils.isEmpty(htUserInfolist)){
            HtUserInfo htUserInfo = new HtUserInfo();
            htUserInfo.setOpenId(htUser.getOpenId());
            htUserInfo.setUserName(htUser.getUserName());
            htUserInfo.setId(ID.getId());
            htUserInfoDao.saveUser(htUserInfo);
            return ApiResultFactory.getSuccess(htUserInfo);
        }
        Optional<HtUserInfo> findFirst = htUserInfolist.stream().findFirst();
        return ApiResultFactory.getSuccess(findFirst.get());
    }


























}
