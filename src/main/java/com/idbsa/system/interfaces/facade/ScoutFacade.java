package com.idbsa.system.interfaces.facade;

import com.idbsa.system.interfaces.rest.dto.ScoutDto;
import com.idbsa.system.interfaces.rest.dto.ScoutPromotionDto;
import com.idbsa.system.interfaces.rest.dto.ScoutUpdateDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.RankBadge;
import com.idbsa.system.persistence.jpa.Scout;
import com.idbsa.system.persistence.jpa.Section;
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
        RankBadge rankBadge = rankBadgesService.findById(scoutDto.getScoutQualification());

        return scoutService.create(scoutDto, group, section,rankBadge);
    }

    public Scout updateScout(ScoutUpdateDto scoutUpdateDto){
        Section section = sectionService.findById(scoutUpdateDto.getSectionId());
        Group group = groupService.findById(scoutUpdateDto.getGroupId());
        RankBadge rankBadge = rankBadgesService.findById(scoutUpdateDto.getScoutQualification());
        Scout scout = scoutService.findById(scoutUpdateDto.getId());

        return scoutService.update(scoutUpdateDto, scout, group, section,rankBadge);
    }


    public Scout updateScoutSection(ScoutPromotionDto scoutPromotionDto, int groupId){
        Section section = sectionService.findById(scoutPromotionDto.getNewSectionId());
        Scout scout = scoutService.findById(scoutPromotionDto.getScoutId());

        return scoutService.promotion(scoutPromotionDto, scout, section);
    }
}
