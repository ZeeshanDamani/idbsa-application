package com.idbsa.system.security;

import com.idbsa.system.exception.ApplicationException;
import com.idbsa.system.exception.error.IdbsaErrorType;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.User;
import com.idbsa.system.security.Filters.JwtTokenProvider;
import com.idbsa.system.security.constants.UserAuthority;
import com.idbsa.system.service.GroupService;
import com.idbsa.system.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

     //Authenticate User
    public User authenticateUser(String bearerToken){
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7, bearerToken.length());
        } else {
            log.error("JWT Token is expired");
            throw new JwtException("Expired or invalid JWT token");
        }
        boolean isAdmin = false;

        Claims claims = jwtTokenProvider.validateToken(bearerToken);
        Optional<User> user = userService.findById(Integer.parseInt(claims.get("userId").toString()));
        if(user.isPresent()){
            List<UserAuthority> userAuthoritiesByToken = (List<UserAuthority>) claims.get("roles");
            List<UserAuthority> userAuthorities = user.get().getAuthorities();

            isAdmin = userAuthorities.contains(UserAuthority.ADMIN) &&
                    userAuthoritiesByToken.contains(UserAuthority.ADMIN.getAuthority());
            if(isAdmin) {
                log.info("user Id {} from group {} requested resource and in authenticate {}", user.get().getId(),
                        user.get().getGroup().getJamatKhana(), isAdmin );
                return user.get();
            } else {
                Group group = groupService.findById((int)claims.get("groupId"));
                if(Objects.isNull(group) && user.get().getGroup().getId() != group.getId() ){
                    log.info("user Id {} from group {} requested resource and in authenticate {}", user.get().getId(),
                            user.get().getGroup().getJamatKhana(), isAdmin );

                    log.info("group id {} in request and in actual are different {}", group.getId(), user.get().getGroup().getId());
                    throw  ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                            .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                            .build();
                }
                log.info("user Id {} from group {} requested resource and is Admin {}", user.get().getId(),
                        user.get().getGroup().getJamatKhana(), isAdmin );
                return user.get();
            }
        }
        log.info("Invalid user Access userID {}", user.get());
        throw  ApplicationException.builder().appMessage(IdbsaErrorType.INVALID_USER.getAppMessage())
                .appCode(IdbsaErrorType.INVALID_USER.getAppCode())
                .build();
    }
}
