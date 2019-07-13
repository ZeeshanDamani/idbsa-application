package com.idbsa.system.interfaces.controller;

import com.idbsa.system.interfaces.rest.dto.LoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login/")
public class LoginController {


    @PostMapping
    public String doLogin(@RequestBody LoginDto loginDto){
        return "";
    }
}
