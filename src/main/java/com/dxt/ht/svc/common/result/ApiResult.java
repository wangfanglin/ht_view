package com.dxt.ht.svc.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 调用API接口的返回信息类
 *
 * 其中包含
 *
 * status：状态信息
 * content：内容信息
 * data：数据
 *
 * 该接口的构造形式必须借助于ResultStatus
 * 原因是为了保证所有返回的status均通过ResultStatus声明
 *
 * 强烈建议，所有该类的实例通过 ApiResultFactory 工厂进行实例化
 *
 *
 * @param <T>
 */
@ApiModel(value = "返回参数说明")
public class ApiResult<T> implements Result{

    @ApiModelProperty(value = "返回状态")
    private String status;

    @ApiModelProperty(value = "返回信息说明")
    private String content;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public ApiResult(ResultStatus status) {
        this(status, (String) null);
    }

    public ApiResult(ResultStatus status,String content) {
        this.status = status.getStatus();
        this.content = content;
    }

    public ApiResult(ResultStatus status,T data) {
        this.status = status.getStatus();
        this.content = status.getContent();
        this.data = data;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{\"ApiResult\":{"
                + "\"status\":\""
                + status + '\"'
                + ",\"content\":\""
                + content + '\"'
                + ",\"data\":"
                + data
                + "}}";
    }
}
