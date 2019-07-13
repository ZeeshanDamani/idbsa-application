package com.idbsa.system.interfaces.facade;

import com.idbsa.system.persistence.jpa.LeaderBadge;
import com.idbsa.system.service.LeaderBadgesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBadgeFacade {


    @Autowired
    LeaderBadgesService leaderBadgesService;

    public List<LeaderBadge> getAllLeaderBadges(){
        return leaderBadgesService.findAll();
    }
}
