package com.idbsa.system.interfaces.controller;


import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.LeaderBadgeFacade;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.LeaderBadge;
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
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.error("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(leaderBadgeFacade.getAllLeaderBadges(), HttpStatus.OK);
    }
}
