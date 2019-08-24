package com.idbsa.system.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(value = {"com.idbsa.system"})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApplicationException.class)
    public final ResponseEntity<ApiResponse> handleNotFoundException(ApplicationException ex, WebRequest request) {

       if(ex.getAppCode().equals("APP-009") || ex.getAppCode().equals("APP-0012")) {
            return new ResponseEntity<ApiResponse>(ApiResponse.builder()
                    .timestamp(System.currentTimeMillis())
                    .responseCode(HttpStatus.UNAUTHORIZED.value())
                    .success(false)
                    .message(ex.getAppMessage())
                    .build(),HttpStatus.UNAUTHORIZED);
        } if(ex.getAppCode().equals("APP-0013") || ex.getAppCode().equals("APP-0014") || ex.getAppCode().equals("APP-001")
        || ex.getAppCode().equals("APP-002") || ex.getAppCode().equals("APP-003") || ex.getAppCode().equals("APP-004")
                || ex.getAppCode().equals("APP-005") || ex.getAppCode().equals("APP-006")){
            return new ResponseEntity<ApiResponse>(ApiResponse.builder()
                    .timestamp(System.currentTimeMillis())
                    .responseCode(HttpStatus.FORBIDDEN.value())
                    .success(false)
                    .message(ex.getAppMessage())
                    .build(),HttpStatus.FORBIDDEN);

        } else {
            return new ResponseEntity<ApiResponse>(ApiResponse.builder()
                    .timestamp(System.currentTimeMillis())
                    .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .success(false)
                    .message(ex.getAppMessage())
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }



    @ExceptionHandler(JwtException.class)
    public final ResponseEntity<ApiResponse> handleJwtException(JwtException ex, WebRequest request) {

            return new ResponseEntity<ApiResponse>(ApiResponse.builder()
                    .timestamp(System.currentTimeMillis())
                    .responseCode(HttpStatus.UNAUTHORIZED.value())
                    .success(false)
                    .message(ex.getMessage())
                    .build() , HttpStatus.UNAUTHORIZED);
    }
}
