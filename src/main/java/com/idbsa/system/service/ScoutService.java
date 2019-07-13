package com.idbsa.system.service;

import com.idbsa.system.interfaces.rest.dto.ScoutDto;
import com.idbsa.system.interfaces.rest.dto.ScoutPromotionDto;
import com.idbsa.system.interfaces.rest.dto.ScoutUpdateDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.RankBadge;
import com.idbsa.system.persistence.jpa.Scout;
import com.idbsa.system.persistence.jpa.Section;
import com.idbsa.system.persistence.repository.ScoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;

@Service
public class ScoutService {

    @Autowired
    ScoutRepository scoutRepository;

    @Autowired
    private Clock clock;

    public List<Scout> findAll(){
        return scoutRepository.findAll();
    }
    public Scout findById(Integer scoutId){
        return scoutRepository.findOne(scoutId);
    }

    public List<Scout> findByGroupId(Integer groupId){
        return scoutRepository.findByGroupId(groupId);
    }

    public List<Scout> findByGroupIdAndSectionId(Integer groupId, Integer sectionId){
        return scoutRepository.findByGroupIdAndSectionId(groupId, sectionId);
    }

    public Scout create(ScoutDto scoutDto, Group group, Section section, RankBadge rankBadge){

        Scout scout = new Scout();
        scout.setFatherName(scoutDto.getFatherName());
        scout.setFullName(scoutDto.getName());
        scout.setAddress(scoutDto.getHomeAddress());
        scout.setCnic(scoutDto.getCnic());
        scout.setDateOfBirth(scoutDto.getDateOfBirth());
        scout.setDateOfJoining(scoutDto.getDateOfJoining());
        scout.setTransferDate(scoutDto.getTransferDate());
        scout.setMobileNumber(scoutDto.getMobileNumber());
        scout.setHomeContact(scoutDto.getHomeContact());
        scout.setActive(true);
        scout.setGroup(group);
        scout.setCreationTime(clock.millis());
        scout.setScoutQualification(rankBadge);
        scout.setSection(section);
        scout.setScoutImageUrl(scoutDto.getScoutImageUrl());
        scout.setNicImageUrl(scoutDto.getScoutNicImageUrl());
        scout.setBloodGroup(scoutDto.getBloodGroup());

        return scoutRepository.save(scout);

    }

    public Scout update(ScoutUpdateDto scoutUpdateDto, Scout fetchedScout, Group group, Section section, RankBadge rankBadge){

        fetchedScout.setFatherName(scoutUpdateDto.getFatherName());
        fetchedScout.setFullName(scoutUpdateDto.getName());
        fetchedScout.setAddress(scoutUpdateDto.getHomeAddress());
        fetchedScout.setCnic(scoutUpdateDto.getCnic());
        fetchedScout.setDateOfBirth(scoutUpdateDto.getDateOfBirth());
        fetchedScout.setDateOfJoining(scoutUpdateDto.getDateOfJoining());
        fetchedScout.setTransferDate(scoutUpdateDto.getTransferDate());
        fetchedScout.setMobileNumber(scoutUpdateDto.getMobileNumber());
        fetchedScout.setHomeContact(scoutUpdateDto.getHomeContact());
        fetchedScout.setActive(true);
        fetchedScout.setGroup(group);
        fetchedScout.setScoutQualification(rankBadge);
        fetchedScout.setSection(section);
        fetchedScout.setUpdatedTime(clock.millis());
        fetchedScout.setScoutImageUrl(scoutUpdateDto.getScoutImageUrl());
        fetchedScout.setNicImageUrl(scoutUpdateDto.getScoutNicImageUrl());
        fetchedScout.setBloodGroup(scoutUpdateDto.getBloodGroup());

        return scoutRepository.save(fetchedScout);
    }


    public Scout promotion(ScoutPromotionDto scoutPromotionDto, Scout scout, Section section){

       scout.setSection(section);
       scout.setScoutQualification(section.getBasicRankBadge());
       return scoutRepository.save(scout);
    }




}
