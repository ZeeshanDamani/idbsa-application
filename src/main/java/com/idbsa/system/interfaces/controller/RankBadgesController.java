package com.idbsa.system.interfaces.controller;


import com.idbsa.system.interfaces.facade.RankBadgesFacade;
import com.idbsa.system.persistence.jpa.RankBadge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rankbadges")
public class RankBadgesController {

    @Autowired
    RankBadgesFacade rankBadgesFacade;

    @GetMapping
    public ResponseEntity<List<RankBadge>> getAllRankBadges(){
        return new ResponseEntity<>(rankBadgesFacade.getAllRankBadges(), HttpStatus.OK);
    }

    @GetMapping(value = "/{sectionId}")
    public ResponseEntity<List<RankBadge>> getAllRankBadgesBySectionId(@PathVariable Integer sectionId){
        return new ResponseEntity<>(rankBadgesFacade.getAllBySectionId(sectionId), HttpStatus.OK);
    }
}
