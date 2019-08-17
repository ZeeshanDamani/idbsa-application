package com.idbsa.system.security.constants;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
    ADMIN(0),
    DC( 1),
    ADC_EC( 2),
    ADC( 3),
    GSL( 4);

    private UserAuthority(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }

}
