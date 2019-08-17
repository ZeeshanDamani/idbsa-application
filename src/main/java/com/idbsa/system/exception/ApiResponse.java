package com.idbsa.system.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse {
    private Long timestamp;
    private String message;
    private int responseCode;
    private boolean success;
}
