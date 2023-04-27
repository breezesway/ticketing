package com.cgz.ticketing.common.exception;

public enum AppExceptionEnum {

    MEMBER_MOBILE_EXIST("会员手机号已注册")
    ;

    private final String errMsg;

    AppExceptionEnum(String errMsg){
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return "AppExceptionEnum{" +
                "errMsg='" + errMsg + '\'' +
                '}';
    }
}
