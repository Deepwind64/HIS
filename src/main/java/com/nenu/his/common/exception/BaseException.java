package com.example.easystudy.common.exception;

import com.example.easystudy.common.enumeration.ErrorCode;
import com.example.easystudy.common.constant.MessageConstant;
/**
 * 业务异常
 * @author deepwind
 */
@SuppressWarnings("unused")
public class BaseException extends RuntimeException {
    private int errorCode;

    public BaseException() {
    }

    public BaseException(String msg, int errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
    public BaseException(ErrorCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int code){
        this.errorCode = code;
    }
    public static void throwArgsError() {
        throw new BaseException(ErrorCode.INVALID_ARGS, MessageConstant.INVALID_ARGS);
    }
    public static void throwBackendException(){
        throw new BaseException(ErrorCode.BACKEND_ERROR, MessageConstant.BACKEND_ERROR);
    }
    public static void throwBackendException(String message){
        throw new BaseException(ErrorCode.BACKEND_ERROR, message);
    }
}
