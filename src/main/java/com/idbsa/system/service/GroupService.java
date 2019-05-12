package com.idbsa.system.service;

import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.rest.dto.GroupDto;
import com.idbsa.system.interfaces.rest.dto.GroupUpdateDto;
import com.idbsa.system.persistence.jpa.District;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.Jurisdiction;
import com.idbsa.system.persistence.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public List<Group> findAll(){
        return groupRepository.findAll();
    }

    public Group findById(Integer grougId){
        return groupRepository.findOne(grougId);
    }

    public List<Group> findByJurisdiction(Integer jurisdictionId){
        List<Group> groups =  groupRepository.findByJurisdictionId(jurisdictionId);
        if(groups == null || groups.size() == 0){
            throw new ApplicationException(IdbsaErrorType.JURISDICTION_NOT_FOUND);
        }
        return groups;
    }

    public Group save(GroupDto groupDto, District district, Jurisdiction jurisdiction){
        Group group = new Group();

        group.setName(groupDto.getName());
        group.setCode(groupDto.getCode());
        group.setActive(true);
        group.setDistrict(district);
        group.setJurisdiction(jurisdiction);
        group.setJamatKhana(groupDto.getJamatkhana());

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
