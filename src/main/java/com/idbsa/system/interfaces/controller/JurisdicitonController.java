package com.idbsa.system.interfaces.controller;


import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.JurisdictionFacade;
import com.idbsa.system.interfaces.rest.dto.JurisdictionSummaryDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.Jurisdiction;
import com.idbsa.system.persistence.jpa.User;
import com.idbsa.system.security.AuthenticationService;
import com.idbsa.system.security.constants.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/jurisdictions")
@Slf4j
@CrossOrigin
public class JurisdicitonController {

    @Autowired
    JurisdictionFacade jurisdictionFacade;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping
    public ResponseEntity<List<Jurisdiction>> getAllJurisdiciton(@RequestHeader String authorization,
                                                                 @RequestHeader Integer groupID){
        User authenticateUser  =  authenticationService.authenticateUser(authorization);
        return new ResponseEntity<>(jurisdictionFacade.getAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/groups/{jurisdictionId}")
    public ResponseEntity<List<Group>> getAllGroups(@RequestHeader String authorization,
                                                    @RequestHeader Integer groupID,
                                                    @PathVariable Integer jurisdictionId){
        User authenticateUser  =  authenticationService.authenticateUser(authorization);
       return new ResponseEntity<>(jurisdictionFacade.getGroupByJurisdiction(jurisdictionId),HttpStatus.OK);
    }

    @PostMapping(value = "/summary/all")
    public  ResponseEntity<List<JurisdictionSummaryDto> > allGroupsSummary(@RequestHeader String authorization,
                                                                           @RequestHeader Integer groupID){
        log.info("Request received for group jurisdiction wise detils of admin");
        User authenticateUser  =  authenticationService.authenticateUser(authorization);

        if(authenticateUser.getAuthorities().contains(UserAuthority.ADMIN) ||
                authenticateUser.getAuthorities().contains(UserAuthority.ADC_EC) ){
            return new ResponseEntity<>(jurisdictionFacade.getAllSummary(),HttpStatus.OK);
        } else {
            throw  ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
    }


    @PostMapping(value = "/summary/{jurisdictionId}")
    public  ResponseEntity<List<JurisdictionSummaryDto> > allJurisdictionSummary(@RequestHeader String authorization,
                                                                           @RequestHeader Integer groupID,
                                                                           @PathVariable Integer jurisdictionId){
        log.info("Request received for group jurisdiction details for id {}", jurisdictionId);
        User authenticateUser  =  authenticationService.authenticateUser(authorization);

        if(authenticateUser.getAuthorities().contains(UserAuthority.ADMIN) ||
                authenticateUser.getAuthorities().contains(UserAuthority.ADC_EC) ){
            return new ResponseEntity<>(jurisdictionFacade.getJurisdictionWiseSummary(jurisdictionId),HttpStatus.OK);
        } else {
            throw  ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
    }
}
