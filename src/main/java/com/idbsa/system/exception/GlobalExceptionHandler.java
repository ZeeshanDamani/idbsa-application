//package com.idbsa.system.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//
//    @ExceptionHandler(ApplicationException.class)
//    public final ResponseEntity<ApiResponse> handleNotFoundException(ApplicationException ex, WebRequest request) {
//
//        if(Integer.parseInt(ex.getAppCode()) == 8) {
//
//            return new ResponseEntity<ApiResponse>(ApiResponse.builder()
//                    .timestamp(System.currentTimeMillis())
//                    .responseCode(HttpStatus.UNAUTHORIZED.value())
//                    .success(false)
//                    .message(ex.getAppMessage())
//                    .build()
//                    , HttpStatus.UNAUTHORIZED);
//        } else {
//            return new ResponseEntity<ApiResponse>(ApiResponse.builder()
//                    .timestamp(System.currentTimeMillis())
//                    .responseCode(HttpStatus..value())
//                    .success(false)
//                    .message(ex.getAppMessage())
//                    .build()
//                    , HttpStatus.UNAUTHORIZED);
//
//        }
//
//    }
//
//
//}
