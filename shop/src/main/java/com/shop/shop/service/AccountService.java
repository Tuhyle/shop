package com.shop.shop.service;

import com.shop.shop.request.UserCreateRequest;

public interface AccountService {

    void registerUser(UserCreateRequest userCreateRequest);
}
