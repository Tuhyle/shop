package com.shop.shop.common;

import com.shop.shop.entity.Account;
import com.shop.shop.repository.AccountRepository;
import com.shop.shop.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserEditValidator implements Validator {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Account.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserCreateRequest user = (UserCreateRequest) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        if (accountRepository.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userRequest.username");
        }
    }
}