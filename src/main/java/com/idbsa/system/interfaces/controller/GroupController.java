package com.idbsa.system.interfaces.controller;


import com.idbsa.system.interfaces.facade.GroupFacade;
import com.idbsa.system.interfaces.rest.dto.GroupDto;
import com.idbsa.system.interfaces.rest.dto.GroupSummaryDto;
import com.idbsa.system.interfaces.rest.dto.GroupUpdateDto;
import com.idbsa.system.persistence.jpa.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/groups")
@CrossOrigin
public class GroupController {

    @Autowired
    GroupFacade groupFacade;

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups(){
        return new ResponseEntity<>(groupFacade.getAllGroups(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> addGroup(@RequestBody GroupDto groupDto){
        return new ResponseEntity<>(groupFacade.addGroup(groupDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Group> updateGroup(
            @RequestBody GroupUpdateDto groupUpdateDto){
        return new ResponseEntity<>(groupFacade.updateGroup(groupUpdateDto),HttpStatus.OK);
    }

    @GetMapping(value = "/{groupId}")
    public  ResponseEntity<Group> getGroupById(@PathVariable Integer groupId){
        return new ResponseEntity<>(groupFacade.getGroupById(groupId),HttpStatus.OK);
    }

    @PostMapping(value = "/summary/{groupId}")
    public  ResponseEntity<GroupSummaryDto> getSummaryByGroupId(@PathVariable Integer groupId){
        return new ResponseEntity<>(groupFacade.getSummaryByGroupId(groupId),HttpStatus.OK);
    }
}
