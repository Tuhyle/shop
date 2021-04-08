package com.shop.shop.service.Impl;

import com.shop.shop.common.CustomerNotFoundException;
import com.shop.shop.common.ModelMapperUtils;
import com.shop.shop.entity.Account;
import com.shop.shop.entity.Cart;
import com.shop.shop.entity.PhotoProduct;
import com.shop.shop.entity.Product;
import com.shop.shop.repository.AccountRepository;
import com.shop.shop.repository.CartRepository;
import com.shop.shop.request.UserCreateRequest;
import com.shop.shop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import response.AccountDTO;
import response.ProductDTO;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository userRepository;

    @Autowired
    CartRepository cartRepository;

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
                    .userRole("ROLE_EMPLOYEE")
                    .mobile(userRequest.getMobile())
                    .build();

            Account save = userRepository.save(user);
            Cart cart = Cart.builder()
                    .account(save)
                    .email(save.getEmail())
                    .firstName(save.getFirstName())
                    .lastName(save.getLastName())
                    .mobile(save.getMobile())
                    .createAt(new Date())
                    .build();
            Cart cartSave = cartRepository.save(cart);
            log.info("Function : Create a new user success");
        }catch (Exception e){
            log.error("Function : Create a new user fail");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    private String getFileURL(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download/")
                .path(fileName)
                .toUriString();
    }
    @Override
    public Page<AccountDTO> getAll(String search,Pageable pageable) {
        try {
            Page<Account> accountPage;
            if (search != null) {
                accountPage=userRepository.search(search,pageable);
            }else {
                accountPage=userRepository.getAllBy(pageable);
            }
            Page<AccountDTO> accountDTOS = accountPage.map(account -> {
                AccountDTO accountDTO = ModelMapperUtils.map(account, AccountDTO.class);
                if(accountDTO.getPhoto()==null){
                    accountDTO.setPhoto(getFileURL("default.jpg"));
                }else {
                    accountDTO.setPhoto(getFileURL(accountDTO.getPhoto()));
                }
                return accountDTO;
            });
            log.info("getList account success");
            return accountDTOS;
        } catch (Exception e) {
            log.error("getList account fail", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AccountDTO getOne() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = userRepository.findByEmail(authentication.getName());
        AccountDTO accountDTO = ModelMapperUtils.map(account, AccountDTO.class);
        if(accountDTO.getPhoto()==null){
            accountDTO.setPhoto(getFileURL("default.jpg"));
        }else {
            accountDTO.setPhoto(getFileURL(accountDTO.getPhoto()));
        }
        return accountDTO;
    }
    @Override
    public void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException {
        Account account = userRepository.findByEmail(email);
        if (account != null) {
            account.setResetPasswordToken(token);
            userRepository.save(account);
        } else {
            throw new CustomerNotFoundException("Could not find any customer with the email " + email);
        }
    }
    @Override
    public Account getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }
    @Override
    public void updatePassword(Account account, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        account.setPassword(encodedPassword);

        account.setResetPasswordToken(null);
        userRepository.save(account);
    }
}
