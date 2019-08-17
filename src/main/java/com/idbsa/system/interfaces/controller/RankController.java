package com.idbsa.system.interfaces.controller;

import com.idbsa.system.interfaces.facade.RankFacade;
import com.idbsa.system.interfaces.rest.dto.RankDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/ranks")
@RestController
@CrossOrigin
public class RankController {

    @Autowired
    RankFacade rankFacade;

    @PostMapping
    public ResponseEntity<List<RankDto>> getAllRanks(){
        return new ResponseEntity<>(rankFacade.getAllRanks(), HttpStatus.OK);
    }

    @PostMapping(value = "/{sectionId}")
    public ResponseEntity<List<RankDto>> getRanksBySection(@PathVariable Integer sectionId){
        return new ResponseEntity<>(rankFacade.getRankBySectionId(sectionId), HttpStatus.OK);
    }

    @PostMapping(value = "/leaders}")
    public ResponseEntity<List<RankDto>> getLeaderRank(){
        return new ResponseEntity<>(rankFacade.getLeaderRank(), HttpStatus.OK);
    }
}
