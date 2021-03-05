package com.shop.shop.service;

import com.shop.shop.DTO.UserRequest;
import com.shop.shop.entity.User;

public interface UserService {

    void registerUser(UserRequest userRequest);
}
