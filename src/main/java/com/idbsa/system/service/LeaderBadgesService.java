package com.idbsa.system.service;

import com.idbsa.system.persistence.jpa.LeaderBadge;
import com.idbsa.system.persistence.repository.LeaderBadgesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBadgesService {

    @Autowired
    LeaderBadgesRepository leaderBadgesRepository;

    public List<LeaderBadge> findAll(){
        return leaderBadgesRepository.findAll();
    }

    public LeaderBadge findById(Integer leaderBadgeId){
        return leaderBadgesRepository.findOne(leaderBadgeId);
    }
}
