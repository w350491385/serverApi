package com.xw.exception;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public class SessionCheckFailException extends Exception {
    public SessionCheckFailException(String message) {
        super(message);
    }

    public SessionCheckFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
