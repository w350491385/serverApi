package com.xw.exception;


import com.xw.ErrorCode;

public class CheckException extends BaseException {

    private final int code;

    public CheckException(ErrorCode apiErrorCode) {
        super(apiErrorCode.getMessage());
        this.code = apiErrorCode.getCode();
    }

    /**
     * 用于一种code对英语多种语义的情况，需要加remark，最后的msg为apiErrorCodeEnum.getMessage()+":"+remark
     *
     * @param errorCode 错误码
     * @param remark        补充remark
     */
    public CheckException(ErrorCode errorCode, String remark) {
        super(errorCode.getMessage() + ":" + remark);
        this.code = errorCode.getCode();
    }

    /**
     * 用于异常转译
     *
     * @param errorCode 错误码
     * @param cause         下层异常栈
     */
    public CheckException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
    }


    /**
     * 用于异常转译切code对英语多种语义的情况，需要加remark，最后的msg为{@link #getMessage()}
     * +":"+message
     *
     * @param errorCode 错误码
     * @param cause         下层异常栈
     * @param message       异常信息
     */
    public CheckException(ErrorCode errorCode, Throwable cause, String message) {
        super(errorCode.getMessage() + ":" + message, cause);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
