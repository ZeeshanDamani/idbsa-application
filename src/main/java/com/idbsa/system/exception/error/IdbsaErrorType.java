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
    UNABLE_TO_TRANSFER(10, "Unable to transfer due to age"),
    UNABLE_TO_ACTIVATE(11, "Unable to set Active"),
    INVALID_USER(12, "Invalid User for Request"),
    INVALID_CREDENTIALS(9, "Invalid Credentials"),
    SCOUT_NOT_FOUND(13, "Scout not Find "),
    SCOUT_CNIC_ALREADY_EXIST(14, "Scout is already registered with CNIC"),
    LEADER_CNIC_ALREADY_EXIST(15, "Leader is already registered with CNIC");



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
