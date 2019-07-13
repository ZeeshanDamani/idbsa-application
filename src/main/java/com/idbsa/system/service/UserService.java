//package com.idbsa.system.service;
//
//import com.idbsa.system.interfaces.rest.dto.LoginDto;
//import com.idbsa.system.interfaces.rest.dto.UserDto;
//import com.idbsa.system.persistence.jpa.User;
//import com.idbsa.system.persistence.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    User createUser(UserDto userDto){
//
//        User user = new User();
//        user.setEmail(userDto.getUserEmail());
//        user.setGroup(userDto.getGroupId());
//        user.setName(userDto.getUserName());
//        user.setPassword(bCryptPasswordEncoder.encode(userDto.getUserPassword()));
//
//        return userRepository.save(user);
//    }
//
//    User findByEmail(LoginDto loginDto){
//
//        return userRepository.findByEmail(loginDto.getUserName());
//    }
//
//
//
//}
