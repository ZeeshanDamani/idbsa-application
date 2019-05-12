package com.idbsa.system.interfaces.controller;


import com.idbsa.system.interfaces.facade.JurisdictionFacade;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/jurisdictions")
public class JurisdicitonController {

    @Autowired
    JurisdictionFacade jurisdictionFacade;

    @GetMapping
    public ResponseEntity<List<Jurisdiction>> getAllJurisdiciton(){
        return new ResponseEntity<>(jurisdictionFacade.getAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/groups/{jurisdictionId}")
    public ResponseEntity<List<Group>> getAllGroups(@PathVariable Integer jurisdictionId){
       return new ResponseEntity<>(jurisdictionFacade.getGroupByJurisdiction(jurisdictionId),HttpStatus.OK);
    }
}
