package com.idbsa.system.interfaces.rest.dto;

import lombok.Data;

@Data
public class UserDto {

    String userName;
    String userEmail;
    String userPassword;
    int groupId;
}
