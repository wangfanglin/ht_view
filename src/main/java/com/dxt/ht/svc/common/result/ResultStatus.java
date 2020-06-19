package com.dxt.ht.svc.common.result;

/**
 *
 * 返回状态的枚举类
 *
 * 该类包含了状态和内容两个信息
 *
 * 所有的返回状态，必须通过该枚举声明后才可以使用
 *
 *
 */
public enum ResultStatus implements Result{
    SUCCESS("SUCCESS","成功"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),
    API_NOT_EXIST("API_NOT_EXIST", "API方法不存在"),
    INVALID_PUBLIC_PARAM("INVALID_PUBLIC_PARAM", "无效公共参数 >> {0}"),
    INVALID_REQUEST_ERROR("INVALID_REQUEST_ERROR", " 请求方式 {0} 错误 ! 请使用 {1} 方式"),
    INVALID_PARAM("INVALID_PARAM", "无效参数 >> 参数[{0}] >> 原因[{1}]"),
    INVALID_SIGN("INVALID_SIGN", "无效签名"),
    NONE_DATA("NONE_DATA", "找不到数据"),
    VERIFIVATION_CODE_ERROR("VERIFIVATION_CODE_ERROR","验证码错误"),
    NONE_CHANGE("NONE_CHANGE", "无效操作"),
    INTERFACE_FALSE("INTERFACE_FALSE","第三方接口调用失败"),
    ALREADY_EXISTS("DUPLICATE_DATA","重复数据"),
    PARSE_FAILURE("PARSE_FAILURE","解析失败");



    ResultStatus(String status, String content) {
        this.status = status;
        this.content = content;
    }
    private String status;

    private String content;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getContent() {
        return content;
    }
}
