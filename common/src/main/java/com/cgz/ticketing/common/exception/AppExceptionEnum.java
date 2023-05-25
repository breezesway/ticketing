package com.cgz.ticketing.common.exception;

public enum AppExceptionEnum {

    MEMBER_MOBILE_EXIST("手机号已注册"),
    MEMBER_MOBILE_NOT_EXIST("手机号不存在，请先获取短信验证码"),
    MEMBER_MOBILE_CODE_ERROR("验证码错误"),
    BUSINESS_STATION_NAME_UNIQUE_ERROR("车站已存在"),
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
