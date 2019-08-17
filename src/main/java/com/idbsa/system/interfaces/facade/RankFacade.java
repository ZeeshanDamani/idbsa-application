package com.idbsa.system.interfaces.facade;


import com.idbsa.system.interfaces.rest.dto.RankDto;
import com.idbsa.system.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankFacade {

    @Autowired
    RankService rankService;


    public List<RankDto> getAllRanks(){
        return rankService.getAllRanks();
    }


    public List<RankDto> getRankBySectionId(Integer sectionId){
        return rankService.getRankBySEction(sectionId);
    }


    public List<RankDto> getLeaderRank(){
        return rankService.getLeaderRank();
    }
}
