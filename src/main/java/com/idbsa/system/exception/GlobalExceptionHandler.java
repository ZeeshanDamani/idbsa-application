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

        if(Integer.parseInt(ex.getAppCode()) == 8) {

            return new ResponseEntity<ApiResponse>(ApiResponse.builder()
                    .timestamp(System.currentTimeMillis())
                    .responseCode(HttpStatus.UNAUTHORIZED.value())
                    .success(false)
                    .message(ex.getAppMessage())
                    .build()
                    , HttpStatus.UNAUTHORIZED);
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
