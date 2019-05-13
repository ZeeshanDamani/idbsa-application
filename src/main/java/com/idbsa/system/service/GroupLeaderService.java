package com.idbsa.system.service;

import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderDto;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderUpdateDto;
import com.idbsa.system.persistence.jpa.*;
import com.idbsa.system.persistence.repository.GroupLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupLeaderService {

    @Autowired
    GroupLeaderRepository groupLeaderRepository;


    public GroupLeader findById(Integer groupLeaderId){
        GroupLeader groupLeader = groupLeaderRepository.findOne(groupLeaderId);
        if(groupLeader == null){
            throw new ApplicationException(IdbsaErrorType.GROUP_LEADER_NOT_FOUND);
        }
        return groupLeader;
    }

    public List<GroupLeader> findAll(){
        List<GroupLeader> groupLeaders = groupLeaderRepository.findAll();
        if(groupLeaders == null || groupLeaders.size() > 0){
            throw new ApplicationException(IdbsaErrorType.GROUP_LEADER_NOT_FOUND);
        }
        return groupLeaders;
    }

    public List<GroupLeader> findByGroupId(Integer groupId){
        List<GroupLeader> groupLeaders = groupLeaderRepository.findByGroupId(groupId);
        if(groupLeaders == null || groupLeaders.size() > 0){
            throw new ApplicationException(IdbsaErrorType.GROUP_LEADER_NOT_FOUND);
        }
        return groupLeaders;
    }

    public GroupLeader create(GroupLeaderDto groupLeaderDto, Group group, Rank rank, RankBadge rankBadge, LeaderBadge leaderBadge){
        GroupLeader groupLeader =  GroupLeader.builder()
                .group(group)
                .leaderQualification(leaderBadge)
                .leaderRank(rank)
                .scoutQualification(rankBadge)
                .leaderQualificationCertNumber(groupLeaderDto.getLeaderQualificationCertNumber())
                .cnic(groupLeaderDto.getCnic())
                .dateOfBirth(groupLeaderDto.getDateOfBirth())
                .dateOfJoining(groupLeaderDto.getDateOfJoining())
                .name(groupLeaderDto.getName())
                .fatherName(groupLeaderDto.getFatherName())
                .mobileNumber(groupLeaderDto.getMobileNumber())
                .isActive(true)
                .homeAddress(groupLeaderDto.getHomeAddress())
                .build();
       groupLeader = groupLeaderRepository.save(groupLeader);
        return groupLeader;
    }


    public GroupLeader update(GroupLeaderUpdateDto groupLeaderUpdateDto, GroupLeader groupLeader, Group group, Rank rank, RankBadge rankBadge, LeaderBadge leaderBadge){

        groupLeader.setActive(true);
        groupLeader.setCnic(groupLeaderUpdateDto.getCnic());
        groupLeader.setDateOfBirth(groupLeaderUpdateDto.getDateOfBirth());
        groupLeader.setDateOfJoining(groupLeaderUpdateDto.getDateOfJoining());
        groupLeader.setFatherName(groupLeaderUpdateDto.getFatherName());
        groupLeader.setName(groupLeaderUpdateDto.getName());
        groupLeader.setHomeAddress(groupLeaderUpdateDto.getHomeAddress());
        groupLeader.setLeaderRank(rank);
        groupLeader.setScoutQualification(rankBadge);
        groupLeader.setLeaderQualification(leaderBadge);
        groupLeader.setLeaderQualificationCertNumber(groupLeaderUpdateDto.getLeaderQualificationCertNumber());
        groupLeader.setMobileNumber(groupLeaderUpdateDto.getMobileNumber());
        groupLeader.setGroup(group);

        groupLeader = groupLeaderRepository.save(groupLeader);
        return groupLeader;
    }



    //:TODO Find by JurisdictionId
    public List<GroupLeader> findByJurisdictionId(){
        List<GroupLeader> groupLeaders = groupLeaderRepository.findAll();
        if(groupLeaders == null || groupLeaders.size() > 0){
            throw new ApplicationException(IdbsaErrorType.GROUP_LEADER_NOT_FOUND);
        }
        return groupLeaders;
    }


}
