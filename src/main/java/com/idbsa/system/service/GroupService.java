package com.idbsa.system.service;

import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.rest.dto.GroupDto;
import com.idbsa.system.interfaces.rest.dto.UnitSummaryDto;
import com.idbsa.system.interfaces.rest.dto.GroupUpdateDto;
import com.idbsa.system.persistence.jpa.*;
import com.idbsa.system.persistence.repository.GroupLeaderRepository;
import com.idbsa.system.persistence.repository.GroupRepository;
import com.idbsa.system.persistence.repository.ScoutRepository;
import com.idbsa.system.persistence.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ScoutRepository scoutRepository;

    @Autowired
    GroupLeaderRepository groupLeaderRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    ScoutService scoutService;

    public List<Group> findAll(){
        return groupRepository.findAll();
    }

    public Group findById(Integer grougId){
        return groupRepository.findOne(grougId);
    }

    public List<UnitSummaryDto> getGroupSummary(Integer groupId) {

        List<UnitSummaryDto> unitSummaryDtoList = new ArrayList<>();
        List<Section> sections = sectionRepository.findAll();

        for (Section section : sections) {
            Integer totalCountInSection = scoutRepository.countBySectionIdAndGroupId(section.getId(),
                    groupId);
            Integer overAgeCountForSection = scoutService.findOverAgeCountByGroup(groupId, section.getId());

            double totalUnits = totalCountInSection / section.getScoutsPerunit();

            if(totalUnits == 0 && totalCountInSection != 0 ){
                totalUnits = 1;
            } else {
                Integer actual = totalCountInSection;
                double extra = totalCountInSection  - actual;
                totalUnits =  extra > 0.7 ? Math.round(totalUnits) : actual;
            }

            unitSummaryDtoList.add(UnitSummaryDto.builder().sectionName(section.getName()).
                    totalCount(totalCountInSection)
                    .totalUnits(totalUnits)
                    .totalFees((int)totalUnits * section.getBasicFees())
                    .totalOverage(overAgeCountForSection)
                    .build());
        }
        unitSummaryDtoList.add(UnitSummaryDto.builder().sectionName("Leaders").
                totalCount(groupLeaderRepository.countByGroupId(groupId))
                .build());
        return unitSummaryDtoList;
    }

    public List<Group> findByJurisdiction(Integer jurisdictionId){
        List<Group> groups =  groupRepository.findByJurisdictionId(jurisdictionId);
        if(groups == null || groups.size() == 0){
            throw new ApplicationException(IdbsaErrorType.JURISDICTION_NOT_FOUND);
        }
        return groups;
    }

    public Group save(GroupDto groupDto, District district, Jurisdiction jurisdiction, City city){
        Group group = new Group();

        group.setName(groupDto.getName());
        group.setCode(groupDto.getCode());
        group.setActive(true);
        group.setDistrict(district);
        group.setJurisdiction(jurisdiction);
        group.setJamatKhana(groupDto.getJamatkhana());
        group.setCity(city);

        return groupRepository.save(group);
    }

    public Group updateGroup(GroupUpdateDto groupUpdateDto, District district, Jurisdiction jurisdiction){
        Group group = groupRepository.findOne(groupUpdateDto.getGroupId());

        group.setName(groupUpdateDto.getName());
        group.setCode(groupUpdateDto.getCode());
        group.setActive(groupUpdateDto.isActive());
        group.setDistrict(district);
        group.setJurisdiction(jurisdiction);

        return groupRepository.save(group);
    }

    public Group setStatus(Integer groupId, Boolean status){
        Group group = groupRepository.findOne(groupId);
        group.setActive(status);
        return groupRepository.save(group);
    }
}
