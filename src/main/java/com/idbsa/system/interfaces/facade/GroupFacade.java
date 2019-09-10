package com.idbsa.system.interfaces.facade;

import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.rest.dto.GroupDto;
import com.idbsa.system.interfaces.rest.dto.GroupUpdateDto;
import com.idbsa.system.interfaces.rest.dto.UnitSummaryDto;
import com.idbsa.system.persistence.jpa.City;
import com.idbsa.system.persistence.jpa.District;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.Jurisdiction;
import com.idbsa.system.service.CityService;
import com.idbsa.system.service.DistrictService;
import com.idbsa.system.service.GroupService;
import com.idbsa.system.service.JurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupFacade {

    @Autowired
    GroupService groupService;

    @Autowired
    DistrictService districtService;

    @Autowired
    CityService cityService;

    @Autowired
    JurisdictionService jurisdictionService;

    public List<Group> getAllGroups(){
        return groupService.findAll();
    }

    public Group getGroupById(Integer groupId){
        return groupService.findById(groupId);
    }

    public List<UnitSummaryDto>  getSummaryByGroupId(Integer groupId){
        return groupService.getGroupSummary(groupId);
    }

    public List<UnitSummaryDto>  getOverallSummary(Integer groupId){
        return groupService.getGroupSummary(groupId);
    }


    public Group addGroup(GroupDto groupDto){
        District district;
        district = districtService.findById(groupDto.getDistrictId());
        if(district == null){
            throw new ApplicationException(IdbsaErrorType.DISTRICT_NOT_FOUND);
        }

        Jurisdiction jurisdiction = jurisdictionService.findById(groupDto.getJurisdictionId());

        City city  = cityService.findById(groupDto.getCityId())
                ;
        if(jurisdiction == null){
            throw new ApplicationException(IdbsaErrorType.DISTRICT_NOT_FOUND);
        }
        return groupService.save(groupDto, district, jurisdiction, city);
    }


    public Group updateGroup(GroupUpdateDto groupUpdateDto){
        District district = districtService.findById(groupUpdateDto.getDistrictId());
        if(district == null){
            throw new ApplicationException(IdbsaErrorType.DISTRICT_NOT_FOUND);
        }

        Jurisdiction jurisdiction = jurisdictionService.findById(groupUpdateDto.getJurisdictionId());
        if(jurisdiction == null){
            throw new ApplicationException(IdbsaErrorType.DISTRICT_NOT_FOUND);
        }
        return groupService.updateGroup(groupUpdateDto, district, jurisdiction);
    }

}
