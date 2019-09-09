package com.idbsa.system.interfaces.controller;

import com.idbsa.system.interfaces.facade.RankFacade;
import com.idbsa.system.interfaces.rest.dto.RankDto;
import com.idbsa.system.persistence.jpa.User;
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
        User authenticateUser  =  authenticationService.authenticateUser(authorization);
        return new ResponseEntity<>(rankFacade.getAllRanks(), HttpStatus.OK);
    }

    @PostMapping(value = "/{sectionId}")
    public ResponseEntity<List<RankDto>> getRanksBySection(@RequestHeader String authorization,
                                                           @RequestHeader Integer groupID,
                                                           @PathVariable Integer sectionId){
        User authenticateUser  =  authenticationService.authenticateUser(authorization);
        return new ResponseEntity<>(rankFacade.getRankBySectionId(sectionId), HttpStatus.OK);
    }

    @PostMapping(value = "/leaders}")
    public ResponseEntity<List<RankDto>> getLeaderRank(@RequestHeader String authorization,
                                                       @RequestHeader Integer groupID){
        User authenticateUser  =  authenticationService.authenticateUser(authorization);
        return new ResponseEntity<>(rankFacade.getLeaderRank(), HttpStatus.OK);
    }
}
