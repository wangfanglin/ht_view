package com.dxt.ht.svc.common.result;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 接口返回信息的工厂类
 *
 * 建议所有接口返回方法使用该工厂构造
 *
 */
public class ApiResultFactory {

   public static  <T> ApiResult<T> newInstance(ResultStatus status){
        return new ApiResult<>(status,status.getContent());
   }
    public static  <T> ApiResult<T> newInstance(ResultStatus status,String content){
        return new ApiResult<>(status,content);
    }


    public static <T> ApiResult<T> getSuccess(){
        return getSuccess(null);
    }

    public static <T> ApiResult<T> getSuccess(T t){
        return new ApiResult<>(ResultStatus.SUCCESS,t);
    }






}
