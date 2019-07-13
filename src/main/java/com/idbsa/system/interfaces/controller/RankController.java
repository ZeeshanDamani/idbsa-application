package com.idbsa.system.interfaces.controller;

import com.idbsa.system.interfaces.facade.RankFacade;
import com.idbsa.system.persistence.jpa.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/ranks")
@RestController
public class RankController {

    @Autowired
    RankFacade rankFacade;

    @GetMapping
    public ResponseEntity<List<Rank>> getAllRanks(){
        return new ResponseEntity<>(rankFacade.getAllRanks(), HttpStatus.OK);
    }
}
