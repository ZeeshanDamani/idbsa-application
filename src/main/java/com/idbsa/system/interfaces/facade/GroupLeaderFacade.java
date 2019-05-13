package com.idbsa.system.interfaces.facade;


import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderDto;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderUpdateDto;
import com.idbsa.system.persistence.jpa.*;
import com.idbsa.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupLeaderFacade {

    @Autowired
    GroupLeaderService groupLeaderService;

    @Autowired
    RankService rankService;

    @Autowired
    LeaderBadgesService leaderBadgesService;

    @Autowired
    RankBadgesService rankBadgesService;

    @Autowired
    GroupService groupService;

    public GroupLeader findById(Integer groupLeaderId){
        return groupLeaderService.findById(groupLeaderId);
    }

    public List<GroupLeader> findAll(){
        return groupLeaderService.findAll();
    }

    public List<GroupLeader> findByGroupId(Integer groupId){
        return groupLeaderService.findByGroupId(groupId);
    }

    public GroupLeader addGroupLeader(GroupLeaderDto groupLeaderDto){

        Rank leaderRank = rankService.getById(groupLeaderDto.getLeaderRankId());

        LeaderBadge leaderQualification = leaderBadgesService.findById(groupLeaderDto.getLeaderQualificationId());

        RankBadge leaderScoutQualification = rankBadgesService.findById(groupLeaderDto.getScoutQualificationId());

        Group leaderGroup = groupService.findById(groupLeaderDto.getGroupId());

       return groupLeaderService.create(groupLeaderDto, leaderGroup, leaderRank, leaderScoutQualification, leaderQualification);

    }


    public GroupLeader update(GroupLeaderUpdateDto groupLeaderUpdateDto){

        Rank leaderRank = rankService.getById(groupLeaderUpdateDto.getLeaderRankId());

        LeaderBadge leaderQualification = leaderBadgesService.findById(groupLeaderUpdateDto.getLeaderQualificationId());

        RankBadge leaderScoutQualification = rankBadgesService.findById(groupLeaderUpdateDto.getScoutQualificationId());

        Group leaderGroup = groupService.findById(groupLeaderUpdateDto.getGroupId());

        GroupLeader groupLeader = groupLeaderService.findById(groupLeaderUpdateDto.getId());

        return groupLeaderService.update(groupLeaderUpdateDto, groupLeader, leaderGroup, leaderRank, leaderScoutQualification, leaderQualification);

    }

    //:TODO Find by JurisdictionId
    public List<GroupLeader> findByJurisdictionId(){
        List<GroupLeader> groupLeaders = groupLeaderService.findAll();
        if(groupLeaders == null || groupLeaders.size() > 0){
            throw new ApplicationException(IdbsaErrorType.GROUP_LEADER_NOT_FOUND);
        }
        return groupLeaders;
    }
}
