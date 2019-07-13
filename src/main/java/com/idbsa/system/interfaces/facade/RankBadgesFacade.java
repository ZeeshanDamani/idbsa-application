package com.idbsa.system.interfaces.facade;

import com.idbsa.system.persistence.jpa.RankBadge;
import com.idbsa.system.service.RankBadgesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankBadgesFacade {

    @Autowired
    RankBadgesService rankBadgesService;

    public List<RankBadge> getAllRankBadges(){
        return rankBadgesService.findAll();
    }

    public List<RankBadge> getAllBySectionId(Integer sectionId){
        return rankBadgesService.getBySectionId(sectionId);
    }
}
