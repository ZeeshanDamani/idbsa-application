package com.idbsa.system.security;

import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.security.Filters.JwtTokenProvider;
import com.idbsa.system.service.GroupService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    GroupService groupService;

     //Authenticate User
    public Group authenticateUuser(String bearerToken){
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7, bearerToken.length());
        } else {
            log.error("JWT Token is expired");
            throw new JwtException("Expired or invalid JWT token");
        }

        Claims claims = jwtTokenProvider.validateToken(bearerToken);

        Group group = groupService.findById((int)claims.get("groupId"));
        if(Objects.isNull(group)){
            throw  ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                    .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                    .build();
        }
        log.info("Request Received from userId {} of group : {}",(int)claims.get("userId"),group.getJamatKhana());
        return group;
    }
}
