package com.shop.shop.service.Impl;

import com.shop.shop.entity.Account;
import com.shop.shop.repository.AccountRepository;
import com.shop.shop.request.UserCreateRequest;
import com.shop.shop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository userRepository;

    @Override
    public void registerUser(UserCreateRequest userRequest) {
        try{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Account account = userRepository.findByEmail(userRequest.getEmail());
            if (account != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            Account user = Account.builder()
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .email(userRequest.getEmail())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .registeredAt(new Date())
                    .admin("USER")
                    .mobile(userRequest.getMobile())
                    .build();

            Account save = userRepository.save(user);
            log.info("Function : Create a new user success");
        }catch (Exception e){
            log.error("Function : Create a new user fail");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
