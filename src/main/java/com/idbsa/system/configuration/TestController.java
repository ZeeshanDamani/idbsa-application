package com.idbsa.system.configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v{apiVersion}/test")
public class TestController {

    @GetMapping
    public String welcomeTest(){

        return "Welcome to Idbsa App";
    }

}
