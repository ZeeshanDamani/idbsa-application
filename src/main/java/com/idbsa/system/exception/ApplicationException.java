package com.idbsa.system.exception;

import com.idbsa.system.exception.error.IdbsaErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationException extends RuntimeException{

    private static final long serialVersionUID = 9188025448573092720L;
    private HttpStatus httpStatus;
    private String appCode;
    private String appMessage;
    private boolean logStackTrace;

    public ApplicationException(IdbsaErrorType errorEnumType) {
        super(errorEnumType.getAppMessage());
        this.setAppCode(errorEnumType.getAppCode());
        this.setAppMessage(errorEnumType.getAppMessage());
        this.setHttpStatus(errorEnumType.getHttpStatus());
        this.logStackTrace = true;
    }

    public ApplicationException(Exception e, IdbsaErrorType errorEnumType) {
        super(e);
        this.httpStatus = errorEnumType.getHttpStatus();
        this.appCode = errorEnumType.getAppCode();
        this.appMessage = errorEnumType.getAppMessage();
        this.logStackTrace = true;
    }

    public String toString() {
        return "ApplicationException(httpStatus=" + this.getHttpStatus() + ", appCode=" + this.getAppCode() + ", appMessage=" + this.getAppMessage() + ", logStackTrace=" + this.isLogStackTrace() + ")";
    }
}
