package com.shop.shop.controller;

import com.shop.shop.request.UserCreateRequest;
import com.shop.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    @Autowired
    AccountService userService;

    @GetMapping  ("/login")
    public String login() {
        return "login";
    }
    @GetMapping  ("/403")
    public String access() {
        return "403";
    }
    @PostMapping("/register")
    String index(@ModelAttribute("userRequest") UserCreateRequest userRequest) {
        userService.registerUser(userRequest);
        return "login";
    }
    @GetMapping(value = "/register")
    public String register(@ModelAttribute("userRequest") UserCreateRequest userRequest) {
        return "register";
    }
}
