package com.xw.dao.distributed.exception;

/**
 * 不支持异常
 * Created by huangdongbin on 2018/4/16.
 */
public class UnSupportException extends RuntimeException {

    public UnSupportException() {
        super();
    }

    public UnSupportException(String message) {
        super(message);
    }

    public UnSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnSupportException(Throwable cause) {
        super(cause);
    }
}
