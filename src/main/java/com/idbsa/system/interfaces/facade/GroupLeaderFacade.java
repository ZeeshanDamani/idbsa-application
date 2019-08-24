package com.idbsa.system.interfaces.facade;


import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.rest.dto.GroupLeaderDto;
import com.idbsa.system.persistence.jpa.*;
import com.idbsa.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

        try {
            Rank leaderRank = rankService.getById(groupLeaderDto.getLeaderRankId());
            if (leaderRank == null) {
                log.error("Rank Id is Invald {} ", groupLeaderDto.getLeaderRankId());
            }
            LeaderBadge leaderQualification = leaderBadgesService.findById(groupLeaderDto.getLeaderQualificationId());
            if (leaderQualification == null) {
                log.error("Leader Qualification Id is Invald {} ", groupLeaderDto.getLeaderQualificationId());
            }
            Group leaderGroup = groupService.findById(groupLeaderDto.getGroupId());

            if (leaderGroup == null) {
                log.error("Leader Group Id  is Invald {} ", groupLeaderDto.getGroupId());
            }

            return groupLeaderService.create(groupLeaderDto, leaderGroup, leaderRank, leaderQualification);
        } catch (SQLGrammarException e){
            throw new ApplicationException(IdbsaErrorType.SCOUT_QUALIFICATION_NOT_FOUND);
        }
    }


    public GroupLeader update(GroupLeaderDto groupLeaderUpdateDto, Integer leaderId){

        Rank leaderRank = rankService.getById(groupLeaderUpdateDto.getLeaderRankId());

        if(leaderRank == null){
            throw new ApplicationException(IdbsaErrorType.RANK_NOT_FOUND);
        }
        GroupLeader groupLeader = groupLeaderService.findById(leaderId);

        LeaderBadge leaderQualification = leaderBadgesService.findById(groupLeaderUpdateDto.getLeaderQualificationId());

        if(leaderQualification == null){
            throw new ApplicationException(IdbsaErrorType.LEADER_QUALIFICATION_NOT_FOUND);
        }

        Group leaderGroup = groupService.findById(groupLeaderUpdateDto.getGroupId());

        if(leaderGroup == null){
            throw new ApplicationException(IdbsaErrorType.GROUP_LEADER_NOT_FOUND);
        }

        return groupLeaderService.update(groupLeaderUpdateDto, groupLeader, leaderGroup, leaderRank,
                leaderQualification);

    }

    public GroupLeader activate(Integer leaderId){
        GroupLeader groupLeader = groupLeaderService.findById(leaderId);

        return groupLeaderService.activate(groupLeader);
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
