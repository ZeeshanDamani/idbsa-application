package com.idbsa.system.interfaces.rest.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonAutoDetect
@JsonIgnoreProperties
@Data
@Builder
public class GroupSummaryDto {

    String sectionName;
    Integer totalCount;

}
