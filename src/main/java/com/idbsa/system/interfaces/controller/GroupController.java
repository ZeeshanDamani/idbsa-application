package com.idbsa.system.interfaces.controller;


import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.GroupFacade;
import com.idbsa.system.interfaces.rest.dto.GroupDto;
import com.idbsa.system.interfaces.rest.dto.GroupUpdateDto;
import com.idbsa.system.interfaces.rest.dto.UnitSummaryDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/groups")
@CrossOrigin
@Slf4j
public class GroupController {

    @Autowired
    GroupFacade groupFacade;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups(@RequestHeader String authorization,
                                                    @RequestHeader String groupID){

        return new ResponseEntity<>(groupFacade.getAllGroups(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> addGroup(@RequestHeader String authorization,
                                          @RequestHeader String groupID,
                                          @RequestBody GroupDto groupDto){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(groupFacade.addGroup(groupDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Group> updateGroup(@RequestHeader String authorization,
                                             @RequestHeader String groupID,
                                             @RequestBody GroupUpdateDto groupUpdateDto){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(groupFacade.updateGroup(groupUpdateDto),HttpStatus.OK);
    }

    @GetMapping(value = "/{groupId}")
    public  ResponseEntity<Group> getGroupById(@RequestHeader String authorization,
                                               @RequestHeader Integer groupID,
                                               @PathVariable Integer groupId){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(groupFacade.getGroupById(groupId),HttpStatus.OK);
    }

    @PostMapping(value = "/summary/{groupId}")
    public  ResponseEntity<List<UnitSummaryDto> > getSummaryByGroupId(@RequestHeader String authorization,
                                                                      @RequestHeader Integer groupID,
                                                                      @PathVariable Integer groupId){
        log.info("Request received for group summary of group Id {}", groupID);
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        return new ResponseEntity<>(groupFacade.getSummaryByGroupId(groupId),HttpStatus.OK);
    }
}
