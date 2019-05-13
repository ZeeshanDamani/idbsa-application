package com.idbsa.system.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonAutoDetect
@JsonIgnoreProperties
@Data
public class GroupLeaderUpdateDto {

    private Integer id;

    private String name;

    private String fatherName;

    private String mobileNumber;

    private Integer groupId;

    private Integer scoutQualificationId;

    private String dateOfJoining;

    private String dateOfBirth;

    private String cnic;

    private Integer leaderRankId;

    private boolean isActive;

    private Integer leaderQualificationId;

    private Integer leaderQualificationCertNumber;

    private String homeAddress;

}
