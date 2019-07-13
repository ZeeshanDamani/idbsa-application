package com.idbsa.system.interfaces.controller;


import com.idbsa.system.interfaces.facade.ScoutFacade;
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
public class ScoutController {

    @Autowired
    ScoutFacade scoutFacade;

    @GetMapping("/{scoutId}")
    public ResponseEntity<Scout> getScoutById(@PathVariable Integer scoutId){
        return new ResponseEntity<>(scoutFacade.findById(scoutId), HttpStatus.OK);
    }

    @GetMapping("/section/sectionId/group/{groupId}")
    public ResponseEntity<List<Scout>> getScoutById(@PathVariable Integer sectionId, @PathVariable Integer groupId){
        return new ResponseEntity<>(scoutFacade.findByGroupIdAdndSectionId(groupId,sectionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Scout> createScout(@RequestBody ScoutDto scoutDto){
        return new ResponseEntity<>(scoutFacade.createScout(scoutDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Scout> createScout(@RequestBody ScoutUpdateDto scoutUpdateDto){
        return new ResponseEntity<>(scoutFacade.updateScout(scoutUpdateDto), HttpStatus.OK);
    }

    @GetMapping("/{groupId}/{scoutId}")
    public void promoteUnit(@RequestBody ScoutPromotionDto scoutPromotionDto){

    }

}
