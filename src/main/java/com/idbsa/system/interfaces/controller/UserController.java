package com.idbsa.system.interfaces.controller;


import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.UserFacade;
import com.idbsa.system.interfaces.rest.dto.LoginDto;
import com.idbsa.system.interfaces.rest.dto.UserDto;
import com.idbsa.system.persistence.jpa.User;
import com.idbsa.system.security.Filters.JwtTokenProvider;
import com.idbsa.system.security.UserAuthenticationService;
import com.idbsa.system.security.constants.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    UserFacade userFacade;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserAuthenticationService users;

    @PostMapping("/user")
    public boolean createUser(@RequestBody UserDto userDto){
        return userFacade.createUser(userDto);
    }

    @GetMapping
    public String welcomeMethod(){
        return "Welcome to Our Application";
    }


    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody LoginDto data) {

        log.info("Login Request Received for user {}",data.getUserName().trim());
        try {
            String username = data.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUserName(), data.getUserPassword()));
            User user = userFacade.getUserByUserName(data.getUserName());
            String token = jwtTokenProvider.createToken(user);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("groupId" , user.getGroup().getId());
            model.put("jamatKhana" , user.getGroup().getJamatKhana());
            model.put("jurisdiction" , user.getGroup().getJurisdiction().getName());
            model.put("token", token);
            model.put("accessLevel" , UserAuthority.valueOf(user.getAuthorities().get(0).toString()).getCode());

            log.info("Login Request Success for user {} of Group id {}, group Name {}",
                    data.getUserName().trim(), user.getGroup().getJamatKhana(),
                    user.getGroup().getJamatKhana());

            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.info("Login Request Failed for user {} because of invalid credentials",data.getUserName().trim());
            throw new ApplicationException(e , IdbsaErrorType.INVALID_CREDENTIALS);
        }
    }
}
