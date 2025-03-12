package com.example.easystudy.common.enumeration;

/**
 * @author deepwind
 */

@SuppressWarnings("unused")
public enum ErrorCode {
    /**
     * 异常错误码
     */
    SUCCESS(0),
    UNIVERSAL_AUTHENTICATION_ERROR(1000),
    NOT_LOGIN(1001),
    ACCOUNT_FORBIDDEN(1002),
    ACCOUNT_QUIT_FOREVER(1003),
    INVALID_TOKEN(1004),
    INVALID_USER_UID(1005),
    ACCESS_DENIED(1006),
    LOGIN_FAILED(1007),
    FRONTEND_ERROR(4000),
    EMPTY_PARAM(4001),
    INVALID_ARGS(4002),
    EMAIL_NO_VERIFY(4003),
    BACKEND_ERROR(5000),
    ;

    ErrorCode(int code){
        this.code = code;
    }
    private final int code;

    public int getCode(){
        return this.code;
    }
}