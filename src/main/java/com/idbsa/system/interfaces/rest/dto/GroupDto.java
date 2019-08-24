package com.idbsa.system.interfaces.rest.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonAutoDetect
@JsonIgnoreProperties
@Data
public class GroupDto {
    private String name;
    private String code;
    private Integer jurisdictionId;
    private Integer districtId;
    private String jamatkhana;
    private Integer cityId;
}
