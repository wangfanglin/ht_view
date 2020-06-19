package com.dxt.ht.svc.config;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class CustomException {

    @ExceptionHandler(value = Exception.class)
    public ApiResult exceptionHandler(HttpServletRequest request, Exception e){


        //绑定异常是需要明确提示给用户的
        if(e instanceof BindException){
            BindException exception=(BindException) e;
            List<ObjectError> errors=exception.getAllErrors();
            String msg=errors.get(0).getDefaultMessage();//获取自错误信息
            log.error(e.getMessage());
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM,msg);
        }
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception=(MethodArgumentNotValidException) e;
            List<ObjectError> errors=exception.getBindingResult().getAllErrors();
            String msg=errors.get(0).getDefaultMessage();//获取自错误信息
            log.error(e.getMessage());
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM,msg);
        }
        if(e instanceof NullPointerException){
            log.error(e.getMessage());
            return  ApiResultFactory.newInstance(ResultStatus.SYSTEM_ERROR);
        }
        if (e instanceof Exception){
            log.error(e.getMessage());
            log.error(e.getLocalizedMessage());
        }
        return  ApiResultFactory.newInstance(ResultStatus.SYSTEM_ERROR);
    }
}
