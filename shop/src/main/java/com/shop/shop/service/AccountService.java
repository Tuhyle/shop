package com.shop.shop.service;

import com.shop.shop.request.UserCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import response.AccountDTO;

public interface AccountService {

    void registerUser(UserCreateRequest userCreateRequest);

    Page<AccountDTO> getAll(String search,Pageable pageable);

    AccountDTO getOne();
}
