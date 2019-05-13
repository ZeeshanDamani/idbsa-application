package com.idbsa.system.interfaces.controller;

import com.idbsa.system.interfaces.facade.GroupLeaderFacade;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderDto;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderUpdateDto;
import com.idbsa.system.persistence.jpa.GroupLeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaders")
public class GroupLeaderController {

    @Autowired
    GroupLeaderFacade groupLeaderFacade;

//    @GetMapping
//    public ResponseEntity<List<GroupLeader>> findAllLeaders(){
//        return new ResponseEntity<>(groupLeaderFacade.findAll(),HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<GroupLeader> addGroupLeader(@RequestBody GroupLeaderDto groupLeaderDto){
        return new ResponseEntity<>(groupLeaderFacade.addGroupLeader(groupLeaderDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<GroupLeader> updateGroupLeader(@RequestBody GroupLeaderUpdateDto groupLeaderUpdateDto){
        return new ResponseEntity<>(groupLeaderFacade.update(groupLeaderUpdateDto), HttpStatus.CREATED);
    }

    @GetMapping(name = "/group/{groupId}")
    public ResponseEntity<List<GroupLeader>> findbyGroupId(@PathVariable Integer groupId){
        return new ResponseEntity<>(groupLeaderFacade.findByGroupId(groupId), HttpStatus.OK);
    }
}
