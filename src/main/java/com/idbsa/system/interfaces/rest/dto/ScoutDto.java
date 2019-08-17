package com.idbsa.system.interfaces.rest.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonAutoDetect
@JsonIgnoreProperties
@Data
public class ScoutDto {

    private String name;
    private String fatherName;
    private String mobileNumber;
    private String homeContact;
    private Integer groupId;
    private String dateOfJoining;
    private String dateOfBirth;
    private String cnic;
    private boolean isActive;
    private Integer scoutQualificationId;
    private String homeAddress;
    private String transferDate;
    private Integer sectionId;
    private String scoutImageUrl;
    private String scoutNicImageUrl;
    private Integer scoutRankId;
    private String emailAddress;
    private String bloodGroup;
}
