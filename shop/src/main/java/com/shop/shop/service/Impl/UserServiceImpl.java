package com.shop.shop.service.Impl;

import com.shop.shop.DTO.UserRequest;
import com.shop.shop.entity.User;
import com.shop.shop.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser(UserRequest userRequest) {
        try{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User check = userRepository.findByEmail(userRequest.getEmail());
            if (check != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            User user = User.builder()
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .email(userRequest.getEmail())
                    .passwordHash(passwordEncoder.encode(userRequest.getPassword()))
                    .registeredAt(new Date())
                    .admin("USER")
                    .mobile(userRequest.getMobile())
                    .build();
            User save = userRepository.save(user);
            log.info("Function : Create a new user success");
        }catch (Exception e){
            log.error("Function : Create a new user");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
