package com.idbsa.system.interfaces.rest.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {

    private String userName;
    private String password;
}
