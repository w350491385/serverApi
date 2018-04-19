package com.xw.exception;

/**
 * Created by Administrator on 2015/12/11.
 */
public abstract class BaseException extends RuntimeException {
    public abstract int getCode() ;


    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
