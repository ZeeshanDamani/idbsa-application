package com.idbsa.system.interfaces.facade;


import com.idbsa.system.persistence.jpa.Rank;
import com.idbsa.system.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankFacade {

    @Autowired
    RankService rankService;


    public List<Rank> getAllRanks(){
        return rankService.getAllRanks();
    }
}
