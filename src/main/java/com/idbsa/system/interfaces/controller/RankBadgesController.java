package com.idbsa.system.interfaces.controller;


import com.idbsa.system.interfaces.facade.RankBadgesFacade;
import com.idbsa.system.persistence.jpa.RankBadge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rankbadges")
@CrossOrigin
public class RankBadgesController {

    @Autowired
    RankBadgesFacade rankBadgesFacade;

    @PostMapping
    public ResponseEntity<List<RankBadge>> getAllRankBadges(){
        return new ResponseEntity<>(rankBadgesFacade.getAllRankBadges(), HttpStatus.OK);
    }

    @PostMapping(value = "/{sectionId}")
    public ResponseEntity<List<RankBadge>> getAllRankBadgesBySectionId(@PathVariable Integer sectionId){
        return new ResponseEntity<>(rankBadgesFacade.getAllBySectionId(sectionId), HttpStatus.OK);
    }
}
