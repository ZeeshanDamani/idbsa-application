package com.idbsa.system.interfaces.facade;

import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.rest.dto.ScoutDto;
import com.idbsa.system.interfaces.rest.dto.ScoutPromotionDto;
import com.idbsa.system.interfaces.rest.dto.ScoutUpdateDto;
import com.idbsa.system.persistence.jpa.*;
import com.idbsa.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    public Scout promoteScoutToNewSection(ScoutPromotionDto scoutPromotionDto) {
        Section section = sectionService.findById(scoutPromotionDto.getNewSectionId());
        Scout scout = scoutService.findById(scoutPromotionDto.getScoutId());
        scout.calculateAgeByFormat();
        if (scout.getAge() >= section.getMinimumAge() && scout.getAge() <= section.getMaximumAge()) {
            scout.setSection(section);
            return scoutService.promoteScout(scout);
        } else {
            throw new ApplicationException().builder()
                    .appMessage(IdbsaErrorType.UNABLE_TO_TRANSFER.getAppMessage())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
        public Scout activate(Integer scoutId){
            Scout scout = scoutService.findById(scoutId);

            return scoutService.activate(scout);
        }
}
