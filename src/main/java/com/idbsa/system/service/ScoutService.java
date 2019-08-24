package com.idbsa.system.service;

import com.idbsa.system.interfaces.rest.dto.ScoutDto;
import com.idbsa.system.interfaces.rest.dto.ScoutUpdateDto;
import com.idbsa.system.persistence.jpa.*;
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
        Scout scout =  scoutRepository.findOne(scoutId);
        scout.calculateAgeByFormat();
        scout.calcualteYearsofService();
        scout.checkIsOverAge();
        return scout;
    }

    public List<Scout> findByGroupId(Integer groupId){

        return calculateUnpersistedData(scoutRepository.findByGroupId(groupId));
    }

    public List<Scout> findByGroupIdAndSectionId(Integer groupId, Integer sectionId){
        return calculateUnpersistedData(scoutRepository.findByGroupIdAndSectionId(groupId, sectionId));
    }

    public Scout create(ScoutDto scoutDto, Group group, Section section, RankBadge rankBadge, Rank rank){

        Scout scout = new Scout();
        scout.setFatherName(scoutDto.getFatherName());
        scout.setName(scoutDto.getName());
        scout.setHomeAddress(scoutDto.getHomeAddress());
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
        scout.setImageUrl(scoutDto.getScoutImageUrl());
        scout.setNicImageUrl(scoutDto.getScoutNicImageUrl());
        scout.setBloodGroup(scoutDto.getBloodGroup());
        scout.setAcademicQualification(scoutDto.getAcademicQualification());
        scout.setEmailAddress(scoutDto.getEmailAddress());
        scout.setRank(rank);

        Scout createdScout =  scoutRepository.save(scout);
        District district = createdScout.getGroup().getDistrict();
        City city = createdScout.getGroup().getCity();
        String grNumber =  district.getCode()+ "-" +city.getCode()
                +"-"+createdScout.getGroup().getCode()+"-"+createdScout.getSection().getCode()
                +"-"+scout.getId();
        createdScout.setGrNumber(grNumber);
        return scoutRepository.save(createdScout);
    }

    public Scout update(ScoutUpdateDto scoutUpdateDto, Scout fetchedScout, Group group, Section section, RankBadge rankBadge, Rank rank){

        fetchedScout.setFatherName(scoutUpdateDto.getFatherName());
        fetchedScout.setName(scoutUpdateDto.getName());
        fetchedScout.setHomeAddress(scoutUpdateDto.getHomeAddress());
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
        fetchedScout.setImageUrl(scoutUpdateDto.getScoutImageUrl());
        fetchedScout.setNicImageUrl(scoutUpdateDto.getScoutNicImageUrl());
        fetchedScout.setBloodGroup(scoutUpdateDto.getBloodGroup());
        fetchedScout.setRank(rank);
        fetchedScout.setEmailAddress(scoutUpdateDto.getEmailAddress());
        fetchedScout.setAcademicQualification(scoutUpdateDto.getAcademicQualification());

        return scoutRepository.save(fetchedScout);
    }


    public Scout promoteScout(Scout scout){

       scout.setScoutQualification(scout.getSection().getBasicRankBadge());
       return scoutRepository.save(scout);
    }

    private List<Scout> calculateUnpersistedData(List<Scout> scouts){
        for(Scout scout : scouts){
            scout.calculateAgeByFormat();
            scout.calcualteYearsofService();
            scout.checkIsOverAge();
        }
        return scouts;
    }


    public Scout activate(Scout scout){
        scout.setActive(!scout.isActive());
        return scoutRepository.save(scout);
    }

    public int findOverAgeCountByGroup(Integer groupId, Integer sectionId){

        List<Scout> scouts = scoutRepository.findByGroupIdAndSectionId(groupId, sectionId);
        int overAgeCount = 0;
        for(Scout scout : scouts){
            overAgeCount = scout.checkIsOverAge() == true ? overAgeCount++ : overAgeCount;
        }
        return overAgeCount;
    }

}
