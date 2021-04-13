package com.shop.shop.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String password;

    private String passwordConfirm;
}

