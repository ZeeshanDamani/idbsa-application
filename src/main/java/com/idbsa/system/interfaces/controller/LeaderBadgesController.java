package com.idbsa.system.interfaces.controller;


import com.idbsa.system.interfaces.facade.LeaderBadgeFacade;
import com.idbsa.system.persistence.jpa.LeaderBadge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/leaderbadges")
@RestController
@CrossOrigin
public class LeaderBadgesController {

    @Autowired
    LeaderBadgeFacade leaderBadgeFacade;

    @PostMapping
    public ResponseEntity<List<LeaderBadge>> getAllLeaderBadges(){
        return new ResponseEntity<>(leaderBadgeFacade.getAllLeaderBadges(), HttpStatus.OK);
    }
}
