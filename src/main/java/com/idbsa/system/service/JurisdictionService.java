package com.idbsa.system.service;


import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.interfaces.rest.dto.JurisdictionSummaryDto;
import com.idbsa.system.persistence.jpa.Jurisdiction;
import com.idbsa.system.persistence.jpa.Section;
import com.idbsa.system.persistence.repository.GroupRepository;
import com.idbsa.system.persistence.repository.JurisdictionRepository;
import com.idbsa.system.persistence.repository.ScoutRepository;
import com.idbsa.system.persistence.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JurisdictionService {

    @Autowired
    JurisdictionRepository jurisdictionRepository;

    @Autowired
    ScoutRepository scoutRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    GroupRepository groupRepository;

    public List<Jurisdiction> findAll() {
        return jurisdictionRepository.findAll();
    }

    public Jurisdiction findById(Integer jusrisdictionId) {
        return jurisdictionRepository.findOne(jusrisdictionId);
    }

    public List<JurisdictionSummaryDto> getOverallSummary() {

        List<JurisdictionSummaryDto> jurisdictionSummaryDtoList = new ArrayList<>();

        List<Jurisdiction> jurisdictions = jurisdictionRepository.findAll();
        List<Section> sections = sectionRepository.findAll();

        for (Jurisdiction jurisdiction : jurisdictions) {
            for (Section section : sections) {
                Integer totalCountInSection = scoutRepository.countBySectionAndJurisdiction(section.getId(), jurisdiction.getId());
                jurisdictionSummaryDtoList.add(JurisdictionSummaryDto.builder()
                        .jurisdictionId(jurisdiction.getId())
                        .jurisdictionName(jurisdiction.getName())
                        .sectionName(section.getName())
                        .totalCount(totalCountInSection)
                        .build());

            }
        }
        return jurisdictionSummaryDtoList;
    }


    public List<JurisdictionSummaryDto> getJurisdictionSummary(Integer jurisdictionId) {


        Jurisdiction jurisdiction = jurisdictionRepository.findOne(jurisdictionId);
        if (Objects.isNull(jurisdiction)) {
            throw  ApplicationException.builder().appMessage(IdbsaErrorType.JURISDICTION_NOT_FOUND.getAppMessage())
                    .appCode(IdbsaErrorType.JURISDICTION_NOT_FOUND.getAppCode())
                    .build();
        }
        List<Section> sections = sectionRepository.findAll();
        List<JurisdictionSummaryDto> jurisdictionSummaryDtoList = new ArrayList<>();

        for (Section section : sections) {
            Integer totalCountInSection = scoutRepository.countBySectionAndJurisdiction(section.getId(), jurisdiction.getId());
            jurisdictionSummaryDtoList.add(JurisdictionSummaryDto.builder()
                    .jurisdictionId(jurisdiction.getId())
                    .jurisdictionName(jurisdiction.getName())
                    .sectionName(section.getName())
                    .totalCount(totalCountInSection)
                    .build());

        }
        return jurisdictionSummaryDtoList;
    }

}
