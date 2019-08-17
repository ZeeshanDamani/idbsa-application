package com.idbsa.system.interfaces.facade;

import com.idbsa.system.interfaces.rest.dto.UserDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.User;
import com.idbsa.system.service.GroupService;
import com.idbsa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    public boolean createUser(UserDto userDto){
        Group group = groupService.findById(userDto.getGroupId());
        return userService.createUser(userDto, group);
    }

    public User getUserByUserName(String userEmail){
        return userService.findByEmail(userEmail).get();

    }
}
