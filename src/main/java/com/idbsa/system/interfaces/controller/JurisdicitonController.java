package com.idbsa.system.interfaces.controller;


import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.JurisdictionFacade;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.Jurisdiction;
import com.idbsa.system.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/jurisdictions")
@Slf4j
public class JurisdicitonController {

    @Autowired
    JurisdictionFacade jurisdictionFacade;

    @Autowired
    AuthenticationService authenticationService;


    @GetMapping
    public ResponseEntity<List<Jurisdiction>> getAllJurisdiciton(@RequestHeader String authorization,
                                                                 @RequestHeader Integer groupID){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.error("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(jurisdictionFacade.getAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/groups/{jurisdictionId}")
    public ResponseEntity<List<Group>> getAllGroups(@RequestHeader String authorization,
                                                    @RequestHeader Integer groupID,
                                                    @PathVariable Integer jurisdictionId){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
       return new ResponseEntity<>(jurisdictionFacade.getGroupByJurisdiction(jurisdictionId),HttpStatus.OK);
    }
}
