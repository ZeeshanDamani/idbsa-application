package com.idbsa.system.service;

import com.idbsa.system.persistence.jpa.RankBadge;
import com.idbsa.system.persistence.repository.RankBadgesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankBadgesService {

    @Autowired
    RankBadgesRepository rankBadgesRepository;

    public List<RankBadge> findAll(){
        return rankBadgesRepository.findAll();
    }

    public RankBadge findById(Integer rankBadgeId){
        return rankBadgesRepository.findOne(rankBadgeId);
    }

    public List<RankBadge> getBySectionId(Integer sectionId){
        return  rankBadgesRepository.findBySectionId(sectionId);
    }
}
