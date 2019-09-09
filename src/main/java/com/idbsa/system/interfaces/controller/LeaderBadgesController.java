package com.idbsa.system.interfaces.controller;


import com.idbsa.system.interfaces.facade.LeaderBadgeFacade;
import com.idbsa.system.persistence.jpa.LeaderBadge;
import com.idbsa.system.persistence.jpa.User;
import com.idbsa.system.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/leaderbadges")
@RestController
@Slf4j
@CrossOrigin
public class LeaderBadgesController {

    @Autowired
    LeaderBadgeFacade leaderBadgeFacade;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping
    public ResponseEntity<List<LeaderBadge>> getAllLeaderBadges(@RequestHeader String authorization,
                                                                @RequestHeader Integer groupID){
        User authenticateUser  =  authenticationService.authenticateUser(authorization);
        return new ResponseEntity<>(leaderBadgeFacade.getAllLeaderBadges(), HttpStatus.OK);
    }
}
