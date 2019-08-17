package com.idbsa.system.interfaces.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessages {

    SCOUT_CREATION("Scout Created Sucessfully"),
    LEADER_CREATION("Leader Created Sucessfully"),
    LEADER_UPDATE("Leader Updated Sucessfully"),
    SCOUT_UPDATED("Scout Updated Sucessfully");

    private String message;


}
