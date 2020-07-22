package com.wangjc.task.base.exception;

/**
 * 异常类
 * @author com.wangjc
 * @title: CommonException
 * @projectName wangjc-blog
 * @description: TODO
 * @date 2019/10/2920:10
 */
public class CommonException extends Exception {

    private static final long serialVersionUID = -4828257283863932181L;

    private Integer errorCode;
    private Boolean isPropertiesKey;

    /**
     * 异常捕获
     * @param message
     */
    public CommonException(String message){
        super(message);
        this.isPropertiesKey = true;
    }

    public CommonException(Integer errorCode, String message) {
        this(errorCode, message, true);
    }

    public CommonException(Integer errorCode, String message, Throwable cause) {
        this(errorCode, message, cause, true);
    }

    public CommonException(Integer errorCode, String message, boolean isPropertiesKey) {
        super(message);
        this.isPropertiesKey = true;
        this.setErrorCode(errorCode);
        this.setPropertiesKey(isPropertiesKey);
    }

    public CommonException(Integer errorCode, String message, Throwable cause, Boolean isPropertiesKey) {
        super(message, cause);
        this.isPropertiesKey = true;
        this.setErrorCode(errorCode);
        this.setPropertiesKey(isPropertiesKey);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
        this.isPropertiesKey = true;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return this.isPropertiesKey;
    }

    public void setPropertiesKey(Boolean isPropertiesKey) {
        this.isPropertiesKey = isPropertiesKey;
    }

}
