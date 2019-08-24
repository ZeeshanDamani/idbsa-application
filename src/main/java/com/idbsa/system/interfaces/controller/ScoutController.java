package com.idbsa.system.interfaces.controller;


import com.idbsa.system.exception.ApiResponse;
import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.ScoutFacade;
import com.idbsa.system.interfaces.rest.ResponseMessages;
import com.idbsa.system.interfaces.rest.dto.ScoutDto;
import com.idbsa.system.interfaces.rest.dto.ScoutPromotionDto;
import com.idbsa.system.interfaces.rest.dto.ScoutUpdateDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.Scout;
import com.idbsa.system.security.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/scout")
@CrossOrigin
@Slf4j
public class ScoutController {

    @Autowired
    ScoutFacade scoutFacade;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/{scoutId}")
    public ResponseEntity<Scout> getScoutById(@RequestHeader String authorization,
                                              @RequestHeader Integer groupID,
                                              @PathVariable Integer scoutId){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)){
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }

        log.info("Getting Scout id  {}for group {}" , scoutId, groupID);
        return new ResponseEntity<>(scoutFacade.findById(scoutId), HttpStatus.OK);
    }

    @PostMapping("/section/{sectionId}/group/{groupId}")
    public ResponseEntity<List<Scout>> getScoutById(@RequestHeader String authorization, @RequestHeader Integer groupID,
                                                    @PathVariable Integer sectionId,
                                                    @PathVariable Integer groupId){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupId)) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        log.info("Getting Scout list  for group {}" , groupID);
        return new ResponseEntity<>(scoutFacade.findByGroupIdAdndSectionId(groupId,sectionId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ApiResponse> createScout(@RequestHeader String authorization,
                                                   @RequestHeader Integer groupID,
                                                   @RequestBody ScoutDto scoutDto){

        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(scoutDto.getGroupId())) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());

            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }

        log.info("Creating  Scout {}  for group {}" , scoutDto, groupID);
        scoutFacade.createScout(scoutDto);
        return new  ResponseEntity<>(ApiResponse.builder()
                .timestamp(System.currentTimeMillis())
                .message(ResponseMessages.SCOUT_CREATION.getMessage())
                .responseCode(HttpStatus.CREATED.value())
                .success(true)
                .build(),
                HttpStatus.CREATED);
    }

    @PostMapping(value = "/{scoutId}")
    public ResponseEntity<ApiResponse> updateScout(@RequestHeader String authorization, @RequestHeader Integer groupID,
                                                   @RequestBody ScoutUpdateDto scoutUpdateDto,
                                                   @PathVariable Integer scoutId){

        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());

            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        log.info("Updating  Scout {}  for group {}" , scoutUpdateDto, groupID);
        scoutFacade.updateScout(scoutUpdateDto,scoutId);
        return new  ResponseEntity<>(ApiResponse.builder()
                .timestamp(System.currentTimeMillis())
                .message(ResponseMessages.SCOUT_CREATION.getMessage())
                .responseCode(HttpStatus.CREATED.value())
                .success(true)
                .build(),
                HttpStatus.OK);
    }

    @PostMapping("/promote/{groupId}/{scoutId}")
    public ResponseEntity<ApiResponse> promoteUnit(@RequestHeader String authorization,
                                                   @RequestHeader Integer groupID,
                                                   @RequestBody ScoutPromotionDto scoutPromotionDto){

        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            log.info("Authentication Error for user of group Id of request {} with invalid credentials {}",groupID,
                    group.getId());

            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        scoutFacade.promoteScoutToNewSection(scoutPromotionDto);
        return new  ResponseEntity<>(ApiResponse.builder()
                .timestamp(System.currentTimeMillis())
                .message(ResponseMessages.SCOUT_UPDATED.getMessage())
                .responseCode(HttpStatus.OK.value())
                .success(true)
                .build(),
                HttpStatus.OK);
    }

    @PostMapping(value = "/activate/{scoutId}")
    public ResponseEntity<ApiResponse> changeActiveType(@RequestHeader String authorization,
                                                        @RequestHeader Integer groupID,
                                                        @PathVariable Integer scoutId){
        Group group = authenticationService.authenticateUuser(authorization);
        if(!group.getId().equals(groupID)) {
            throw ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        Scout scout = scoutFacade.activate(scoutId);
        if(Objects.nonNull(scout)){
            return new  ResponseEntity<>(ApiResponse.builder()
                    .timestamp(System.currentTimeMillis())
                    .message(ResponseMessages.SCOUT_ACTIVE.getMessage() + " " + scout.isActive())
                    .responseCode(HttpStatus.CREATED.value())
                    .success(true)
                    .build(),
                    HttpStatus.OK);
        } else {
            throw new ApplicationException().builder()
                    .appMessage(IdbsaErrorType.UNABLE_TO_TRANSFER.getAppMessage())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }

}
