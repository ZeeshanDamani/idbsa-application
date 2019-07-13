package com.idbsa.system.service;

import com.idbsa.system.persistence.jpa.Section;
import com.idbsa.system.persistence.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    public List<Section> findAll(){
        return sectionRepository.findAll();
    }

    public Section findById(Integer sectionId){
        return sectionRepository.findOne(sectionId);
    }
}
