package com.idbsa.system.interfaces.controller;

import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.RankFacade;
import com.idbsa.system.interfaces.rest.dto.RankDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/ranks")
@RestController
@CrossOrigin
@Slf4j
public class RankController {

    @Autowired
    RankFacade rankFacade;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping
    public ResponseEntity<List<RankDto>> getAllRanks(@RequestHeader String authorization,
                                                     @RequestHeader Integer groupID){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.error("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(rankFacade.getAllRanks(), HttpStatus.OK);
    }

    @PostMapping(value = "/{sectionId}")
    public ResponseEntity<List<RankDto>> getRanksBySection(@RequestHeader String authorization,
                                                           @RequestHeader Integer groupID,
                                                           @PathVariable Integer sectionId){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.error("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(rankFacade.getRankBySectionId(sectionId), HttpStatus.OK);
    }

    @PostMapping(value = "/leaders}")
    public ResponseEntity<List<RankDto>> getLeaderRank(@RequestHeader String authorization,
                                                       @RequestHeader Integer groupID){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.error("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(rankFacade.getLeaderRank(), HttpStatus.OK);
    }
}
