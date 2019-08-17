package com.idbsa.system.service;

import com.idbsa.system.interfaces.rest.dto.RankDto;
import com.idbsa.system.persistence.jpa.Rank;
import com.idbsa.system.persistence.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankService {

    @Autowired
    RankRepository rankRepository;

    public List<RankDto> getAllRanks(){
        List<Rank> ranks =  rankRepository.findAll();
        List<RankDto> rankDtosList  = new ArrayList<>();
        for(int i = 0 ; i < ranks.size() ; i++){
            rankDtosList.add(RankDto.builder()
                    .id(ranks.get(i).getId())
                    .isLeaderRank(ranks.get(i).isLeaderRank())
                    .name(ranks.get(i).getName())
                    .sectionId(ranks.get(i).getSection().getId())
                    .build());

        }
        return rankDtosList;
    }


    public List<RankDto> getRankBySEction(Integer sectionId){
        List<Rank> ranks =  rankRepository.findBySectionId(sectionId);
        List<RankDto> rankDtosList  = new ArrayList<>();
        for(int i = 0 ; i < ranks.size() ; i++){
            rankDtosList.add(RankDto.builder()
                    .id(ranks.get(i).getId())
                    .isLeaderRank(ranks.get(i).isLeaderRank())
                    .name(ranks.get(i).getName())
                    .sectionId(ranks.get(i).getSection().getId())
                    .build());

        }
        return rankDtosList;
    }


    public List<RankDto> getLeaderRank(){
        List<Rank> ranks =  rankRepository.findByLeaderRankTrueAndActiveTrue();
        List<RankDto> rankDtosList  = new ArrayList<>();
        for(int i = 0 ; i < ranks.size() ; i++){
            rankDtosList.add(RankDto.builder()
                    .id(ranks.get(i).getId())
                    .isLeaderRank(ranks.get(i).isLeaderRank())
                    .name(ranks.get(i).getName())
                    .sectionId(ranks.get(i).getSection().getId())
                    .build());

        }
        return rankDtosList;
    }



    public Rank getById(Integer rankId){
        return rankRepository.findOne(rankId);
    }
//
//    public List<Rank> getBySectionId(Integer sectionId){
//        return rankRepository.findBySectionId(sectionId);
//    }
}
