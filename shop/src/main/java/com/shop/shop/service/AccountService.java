package com.shop.shop.service;

import com.shop.shop.common.CustomerNotFoundException;
import com.shop.shop.entity.Account;
import com.shop.shop.request.UserCreateRequest;
import com.shop.shop.request.UserEditRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import response.AccountDTO;

public interface AccountService {

    void registerUser(UserCreateRequest userCreateRequest);

    Page<AccountDTO> getAll(String search,Pageable pageable);

    AccountDTO getOne();

    void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException;

    Account getByResetPasswordToken(String token);

    void updatePassword(Account account, String newPassword);

    AccountDTO editAccount(UserEditRequest userEditRequest);
}
