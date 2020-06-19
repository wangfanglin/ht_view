package com.dxt.ht.svc.qiNiuYun.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.qiNiuYun.config.Config;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "七牛云服务")
@RequestMapping("api/qiNiuYun")
public class QiNiuYunService {

    @Autowired
    Config config;

    @GetMapping("getQiNiuYunToken")
    @ApiOperation(value = "获取七牛云上传Token",notes = "获取七牛云上传Token")
    public ApiResult<String> getQiNiuYunToken(){
        String accessKey = config.getAccessKey();
        String secretKey = config.getSecretKey();
        String bucket = config.getBucketName();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        if(StringUtils.isEmpty(upToken)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        return ApiResultFactory.getSuccess(upToken);
    }
}


