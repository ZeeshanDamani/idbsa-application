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
    private Long dateOfJoining;
    private Long dateOfBirth;
    private String cnic;
    private Integer leaderRankId;
    private boolean isActive;
    private Integer leaderQualificationId;
    private String leaderQualificationCertNumber;
    private String homeAddress;
    private String leaderImageUrl;
    private String leaderNicImageUrl;
    private String leaderQualificationImageUrl;
    private String bloodGroup;

}
