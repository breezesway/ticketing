package com.cgz.ticketing.common.exception;

import com.cgz.ticketing.common.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 所有异常统一处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(Exception e) {
        LOG.error("系统异常：", e);
        return new CommonResp<>(false,"系统出现异常，请联系管理员");
    }

    /**
     * 应用异常统一处理
     */
    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(AppException e) {
        LOG.error("应用程序异常：{}", e.getE().getErrMsg());
        return new CommonResp<>(false,e.getE().getErrMsg());
    }

    /**
     * 校验异常统一处理
     */
    /*@ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BindException e) {
        CommonResp commonResp = new CommonResp();
        LOG.error("校验异常：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }*/

}
