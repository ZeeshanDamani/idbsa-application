package com.idbsa.system.service;

import com.idbsa.system.persistence.jpa.Rank;
import com.idbsa.system.persistence.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {

    @Autowired
    RankRepository rankRepository;

    public List<Rank> getAllRanks(){
        return rankRepository.findAll();
    }

    public Rank getById(Integer rankId){
        return rankRepository.findOne(rankId);
    }
//
//    public List<Rank> getBySectionId(Integer sectionId){
//        return rankRepository.findBySectionId(sectionId);
//    }
}
