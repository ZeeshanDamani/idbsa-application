package com.idbsa.system.interfaces.facade;

import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.Jurisdiction;
import com.idbsa.system.service.GroupService;
import com.idbsa.system.service.JurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurisdictionFacade {

    @Autowired
    GroupService groupService;

    @Autowired
    JurisdictionService jurisdictionService;

    public List<Group> getGroupByJurisdiction(Integer jurisdictionId){
        return groupService.findByJurisdiction(jurisdictionId);
    }

    public List<Jurisdiction> getAll(){
        return jurisdictionService.findAll();
    }

    public Jurisdiction getById(Integer jurisdictionId){
        return jurisdictionService.findById(jurisdictionId);
    }
}
