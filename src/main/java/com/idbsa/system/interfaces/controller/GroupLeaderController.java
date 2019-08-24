package com.idbsa.system.interfaces.controller;

import com.idbsa.system.exception.ApiResponse;
import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.GroupLeaderFacade;
import com.idbsa.system.interfaces.rest.ResponseMessages;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderDto;
import com.idbsa.system.persistence.jpa.GroupLeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/leaders")
@CrossOrigin
public class GroupLeaderController {

    @Autowired
    GroupLeaderFacade groupLeaderFacade;

//    @GetMapping
//    public ResponseEntity<List<GroupLeader>> findAllLeaders(){
//        return new ResponseEntity<>(groupLeaderFacade.findAll(),HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<ApiResponse> addGroupLeader(@RequestBody GroupLeaderDto groupLeaderDto){
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
    public ResponseEntity<ApiResponse> updateGroupLeader(@RequestBody GroupLeaderDto groupLeaderUpdateDto,
                                                         @PathVariable Integer leaderId){
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
    public ResponseEntity<List<GroupLeader>> findByGroupId(@PathVariable Integer groupId){
        return new ResponseEntity<>(groupLeaderFacade.findByGroupId(groupId), HttpStatus.OK);
    }

    @PostMapping(value = "/activate/{leaderId}")
    public ResponseEntity<ApiResponse> changeActiveType(@PathVariable Integer leaderId){
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
