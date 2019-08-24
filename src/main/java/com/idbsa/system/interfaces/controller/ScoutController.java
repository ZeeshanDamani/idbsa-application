package com.idbsa.system.interfaces.controller;


import com.idbsa.system.exception.ApiResponse;
import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.facade.ScoutFacade;
import com.idbsa.system.interfaces.rest.ResponseMessages;
import com.idbsa.system.interfaces.rest.dto.ScoutDto;
import com.idbsa.system.interfaces.rest.dto.ScoutPromotionDto;
import com.idbsa.system.interfaces.rest.dto.ScoutUpdateDto;
import com.idbsa.system.persistence.jpa.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/scout")
@CrossOrigin
public class ScoutController {

    @Autowired
    ScoutFacade scoutFacade;

    @GetMapping("/{scoutId}")
    public ResponseEntity<Scout> getScoutById(@PathVariable Integer scoutId){
        return new ResponseEntity<>(scoutFacade.findById(scoutId), HttpStatus.OK);
    }

    @PostMapping("/section/{sectionId}/group/{groupId}")
    public ResponseEntity<List<Scout>> getScoutById(@PathVariable Integer sectionId, @PathVariable Integer groupId){
        return new ResponseEntity<>(scoutFacade.findByGroupIdAdndSectionId(groupId,sectionId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ApiResponse> createScout(@RequestBody ScoutDto scoutDto){
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
    public ResponseEntity<ApiResponse> updateScout(@RequestBody ScoutUpdateDto scoutUpdateDto, @PathVariable Integer scoutId){
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
    public ResponseEntity<ApiResponse> promoteUnit(@RequestBody ScoutPromotionDto scoutPromotionDto){
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
    public ResponseEntity<ApiResponse> changeActiveType(@PathVariable Integer scoutId){
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
