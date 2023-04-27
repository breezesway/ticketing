package com.cgz.ticketing.common.exception;

public class AppException extends RuntimeException{

    private AppExceptionEnum e;

    public AppException(AppExceptionEnum e) {
        this.e = e;
    }

    public AppExceptionEnum getE() {
        return e;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
