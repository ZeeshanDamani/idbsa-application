package com.idbsa.system.service;


import com.idbsa.system.persistence.jpa.Jurisdiction;
import com.idbsa.system.persistence.repository.JurisdictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurisdictionService {

    @Autowired
    JurisdictionRepository jurisdictionRepository;
    
    public List<Jurisdiction> findAll(){
        return jurisdictionRepository.findAll();
    }

    public Jurisdiction findById(Integer jusrisdictionId){
        return jurisdictionRepository.findOne(jusrisdictionId);
    }
}
