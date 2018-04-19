package com.xw.exception;


import com.xw.ErrorCode;

public class ModelException extends BaseException {
    private final int code;

    public ModelException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * 自定义的错误码、错误信息
     *
     * @param errorCode 外部输入的错误码
     * @param fullMessage 外部输入的完整错误信息
     */
    public ModelException(int errorCode, String fullMessage) {
        super(fullMessage);
        this.code = errorCode;
    }

    /**
     * 用于一种code对英语多种语义的情况，需要加remark，最后的msg为apiErrorCodeEnum.getMessage()+":"+remark
     *
     * @param errorCode
     * @param remark    补充remark
     */
    public ModelException(ErrorCode errorCode, String remark) {
        super(errorCode.getMessage() + ":" + remark);
        this.code = errorCode.getCode();
    }

    /**
     * 用于异常转译
     *
     * @param errorCode
     * @param cause
     */
    public ModelException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
    }


    /**
     * 用于异常转译切code对英语多种语义的情况，需要加remark，最后的msg为{@link #getMessage()}
     * +":"+message
     *
     * @param errorCode
     * @param cause
     * @param message
     */
    public ModelException(ErrorCode errorCode, Throwable cause, String message) {
        super(errorCode.getMessage() + ":" + message, cause);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
