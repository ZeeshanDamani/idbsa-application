package com.idbsa.system.interfaces.controller;

import com.idbsa.system.exception.ApiResponse;
import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.GroupLeaderFacade;
import com.idbsa.system.interfaces.rest.ResponseMessages;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.GroupLeader;
import com.idbsa.system.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/leaders")
@CrossOrigin
@Slf4j
public class GroupLeaderController {

    @Autowired
    GroupLeaderFacade groupLeaderFacade;

    @Autowired
    AuthenticationService authenticationService;


//    @GetMapping
//    public ResponseEntity<List<GroupLeader>> findAllLeaders(){
//        return new ResponseEntity<>(groupLeaderFacade.findAll(),HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<ApiResponse> addGroupLeader(@RequestHeader String authorization,
                                                      @RequestHeader Integer groupID,
                                                      @RequestBody GroupLeaderDto groupLeaderDto){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        log.info("Adding Leader {} for group Id {}" , groupLeaderDto , groupID);
        groupLeaderFacade.addGroupLeader(groupLeaderDto);
        return new  ResponseEntity<>(ApiResponse.builder()
                .timestamp(System.currentTimeMillis())
                .message(ResponseMessages.LEADER_CREATION.getMessage())
                .responseCode(HttpStatus.CREATED.value())
                .success(true)
                .build(),
                HttpStatus.CREATED);
    }

    @PostMapping(value = "/{leaderId}")
    public ResponseEntity<ApiResponse> updateGroupLeader(@RequestHeader String authorization,
                                                         @RequestHeader Integer groupID,
                                                         @RequestBody GroupLeaderDto groupLeaderUpdateDto,
                                                         @PathVariable Integer leaderId){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        log.info("Updating Leader {} for group Id {}" , groupLeaderUpdateDto , groupID);
        groupLeaderFacade.update(groupLeaderUpdateDto, leaderId);
        return new  ResponseEntity<>(ApiResponse.builder()
                .timestamp(System.currentTimeMillis())
                .message(ResponseMessages.LEADER_UPDATE.getMessage())
                .responseCode(HttpStatus.OK.value())
                .success(true)
                .build(),
                HttpStatus.CREATED);
    }

    @PostMapping    (value = "/group/{groupId}")
    public ResponseEntity<List<GroupLeader>> findByGroupId(@RequestHeader String authorization,
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
        log.info("getting Leaders List  for group Id {}" , groupID);
        return new ResponseEntity<>(groupLeaderFacade.findByGroupId(groupId), HttpStatus.OK);
    }

    @PostMapping(value = "/activate/{leaderId}")
    public ResponseEntity<ApiResponse> changeActiveType(@RequestHeader String authorization,
                                                        @RequestHeader Integer groupID,
                                                        @PathVariable Integer leaderId){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        log.info("Setting activation for Leader id  {}  for group Id {}" , leaderId, groupID);
        GroupLeader groupLeader = groupLeaderFacade.activate(leaderId  );
        if(Objects.nonNull(groupLeader)){
            return new  ResponseEntity<>(ApiResponse.builder()
                    .timestamp(System.currentTimeMillis())
                    .message(ResponseMessages.GROUP_LEADER_ACTIVE.getMessage() + " " + groupLeader.isActive())
                    .responseCode(HttpStatus.CREATED.value())
                    .success(true)
                    .build(),
                    HttpStatus.OK);
        } else {
            throw new ApplicationException().builder()
                    .appMessage(IdbsaErrorType.UNABLE_TO_ACTIVATE.getAppMessage())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }
}
