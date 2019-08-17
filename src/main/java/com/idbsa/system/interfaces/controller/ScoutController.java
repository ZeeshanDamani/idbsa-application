package com.idbsa.system.interfaces.controller;


import com.idbsa.system.exception.ApiResponse;
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
    public ResponseEntity<ApiResponse> updateeScout(@RequestBody ScoutUpdateDto scoutUpdateDto, @PathVariable Integer scoutId){
        scoutFacade.updateScout(scoutUpdateDto,scoutId);
        return new  ResponseEntity<>(ApiResponse.builder()
                .timestamp(System.currentTimeMillis())
                .message(ResponseMessages.SCOUT_CREATION.getMessage())
                .responseCode(HttpStatus.CREATED.value())
                .success(true)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{groupId}/{scoutId}")
    public void promoteUnit(@RequestBody ScoutPromotionDto scoutPromotionDto){

    }

}
