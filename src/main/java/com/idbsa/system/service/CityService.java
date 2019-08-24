package com.idbsa.system.service;


import com.idbsa.system.persistence.jpa.City;
import com.idbsa.system.persistence.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public City findById(Integer id){
        return cityRepository.findOne(id);
    }
}
