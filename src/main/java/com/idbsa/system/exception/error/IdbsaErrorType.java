package com.idbsa.system.exception.error;

import org.springframework.http.HttpStatus;

public enum IdbsaErrorType {

    DISTRICT_NOT_FOUND(1, "Invalid District Id"),
    JURISDICTION_NOT_FOUND(2, "Invalid Jurisdiction Id"),
    GROUP_LEADER_NOT_FOUND(3, "Invalid Group Leader Id");

    private int code;
    private String errorMessage;

    private static final String PREFIX = "APP-";

    IdbsaErrorType(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

     public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public int getCode() {
        return code;
    }

    public String getAppCode() {
        return PREFIX + String.format("%04d", code);
    }

    public String getAppMessage() {
        return errorMessage;
    }
}
