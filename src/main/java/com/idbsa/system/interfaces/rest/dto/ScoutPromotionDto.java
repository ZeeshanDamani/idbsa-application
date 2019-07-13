package com.idbsa.system.interfaces.rest.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonAutoDetect
@JsonIgnoreProperties
@Data
public class ScoutPromotionDto {

    private int scoutId;
    private int newSectionId;
    private String updateBy;
}
