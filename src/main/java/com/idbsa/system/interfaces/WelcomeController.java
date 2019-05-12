package com.idbsa.system.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
public class WelcomeController {

    @GetMapping
    public String welcomeTest(){

        return "Welcome to Idbsa App";
    }

}
