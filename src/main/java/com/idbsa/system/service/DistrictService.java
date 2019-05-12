package com.idbsa.system.service;


import com.idbsa.system.persistence.jpa.District;
import com.idbsa.system.persistence.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    public List<District> findAll(){
        return districtRepository.findAll();
    }

    public District findById(Integer districtId){
        return districtRepository.findOne(districtId);
    }
}
