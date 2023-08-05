package com.cgz.ticketing.common.exception;

import cn.hutool.core.util.StrUtil;
import com.cgz.ticketing.common.resp.CommonResp;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
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
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(Exception e) throws Exception {
        LOG.info("seata全局事务ID: {}", RootContext.getXID());
        // 如果是在一次全局事务里出异常了，就不要包装返回值，将异常抛给调用方，让调用方回滚事务
        if (StrUtil.isNotBlank(RootContext.getXID())) {
            throw e;
        }
        CommonResp<Object> commonResp = new CommonResp<>();
        LOG.error("系统异常：", e);
        commonResp.setSuccess(false);
        commonResp.setMessage("系统出现异常，请联系管理员");
        return commonResp;
    }

    /**
     * 应用异常统一处理
     */
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(AppException e) {
        LOG.error("应用程序异常：{}", e.getE().getErrMsg());
        return new CommonResp<>(false,e.getE().getErrMsg());
    }

    /**
     * 校验异常统一处理
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(BindException e) {
        StringBuilder msg = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(v->msg.append(v.getDefaultMessage()).append("; "));
        LOG.error("校验异常：{}", msg);
        return new CommonResp<>(false,msg.toString());
    }

}
