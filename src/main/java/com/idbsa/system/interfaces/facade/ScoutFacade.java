package com.idbsa.system.interfaces.facade;

import com.idbsa.system.interfaces.rest.dto.ScoutDto;
import com.idbsa.system.interfaces.rest.dto.ScoutPromotionDto;
import com.idbsa.system.interfaces.rest.dto.ScoutUpdateDto;
import com.idbsa.system.persistence.jpa.*;
import com.idbsa.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoutFacade {

    @Autowired
    ScoutService scoutService;

    @Autowired
    RankService rankService;

    @Autowired
    SectionService sectionService;

    @Autowired
    RankBadgesService rankBadgesService;


    @Autowired
    GroupService groupService;

    public List<Scout> findAllScouts(){
        return scoutService.findAll();
    }

    public List<Scout> findByGroupId(Integer groupId){
        return scoutService.findByGroupId(groupId);
    }

    public Scout findById(Integer scoutId){
        return scoutService.findById(scoutId);
    }

    public List<Scout> findByGroupIdAdndSectionId(Integer groupId, Integer sectionId){
        return scoutService.findByGroupIdAndSectionId(groupId, sectionId);
    }

    public Scout createScout(ScoutDto scoutDto){
        Section section = sectionService.findById(scoutDto.getSectionId());
        Group group = groupService.findById(scoutDto.getGroupId());
        RankBadge rankBadge = rankBadgesService.findById(scoutDto.getScoutQualificationId());
        Rank rank  = rankService.getById(scoutDto.getScoutRankId());

        return scoutService.create(scoutDto, group, section,rankBadge,rank);
    }

    public Scout updateScout(ScoutUpdateDto scoutUpdateDto, Integer scoutId){
        Section section = sectionService.findById(scoutUpdateDto.getSectionId());
        Group group = groupService.findById(scoutUpdateDto.getGroupId());
        RankBadge rankBadge = rankBadgesService.findById(scoutUpdateDto.getScoutQualificationId());
        Scout scout = scoutService.findById(scoutId);
        Rank rank  = rankService.getById(scoutUpdateDto.getScoutRankId());

        return scoutService.update(scoutUpdateDto, scout, group, section,rankBadge, rank);
    }


    public Scout updateScoutSection(ScoutPromotionDto scoutPromotionDto, int groupId){
        Section section = sectionService.findById(scoutPromotionDto.getNewSectionId());
        Scout scout = scoutService.findById(scoutPromotionDto.getScoutId());

        return scoutService.promotion(scoutPromotionDto, scout, section);
    }
}
