package com.idbsa.system.service;

import com.idbsa.system.interfaces.rest.dto.UserDto;
import com.idbsa.system.persistence.jpa.Group;
import com.idbsa.system.persistence.jpa.User;
import com.idbsa.system.persistence.repository.UserRepository;
import com.idbsa.system.security.constants.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

   public Boolean createUser(UserDto userDto, Group group){


        User user = new User();

        user.setUsername(userDto.getUserEmail());
        user.setGroup(group);
        user.setEnabled(true);
        user.setExpired(false);
        user.setLocked(false);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getUserPassword()));
        ArrayList<UserAuthority> authorityList = new ArrayList<>();
        authorityList.add(UserAuthority.valueOf(userDto.getAccessLevel()));
        user.setAuthorities(authorityList);

        User createdUser = userRepository.save(user);
        if(createdUser != null){
            return true;
        } else {
            return false;
        }
    }

    public Optional<User> findByEmail(String userEmail){
       try {
           return userRepository.findByUsername(userEmail);

       }catch (Exception e ){
           log.error(e.toString());
       }
       return null;
       }



    //
//    @Override
//    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(userEmail);
//        if (user == null) {
//            throw new UsernameNotFoundException(userEmail);
//        }
//        return new MyUserPrincipal(user);
//    }

}
