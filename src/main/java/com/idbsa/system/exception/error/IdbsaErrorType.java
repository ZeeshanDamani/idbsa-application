package com.idbsa.system.exception.error;

import org.springframework.http.HttpStatus;

public enum IdbsaErrorType {

    DISTRICT_NOT_FOUND(1, "Invalid District Id"),
    JURISDICTION_NOT_FOUND(2, "Invalid Jurisdiction Id"),
    GROUP_LEADER_NOT_FOUND(3, "Invalid Group Leader Id"),
    RANK_NOT_FOUND(4, "Invalid Rank Id"),
    LEADER_QUALIFICATION_NOT_FOUND(5, "Invalid Leader Qualification Id"),
    SCOUT_QUALIFICATION_NOT_FOUND(6, "Invalid Scout Qualification Id"),
    ENTITY_NOT_FOUND(7, "Invalid Id"),
    NOT_AUTHORIZE(8, "Authorization Error"),
    INVALID_CREDENTIALS(8, "Invalid Credentials");


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
