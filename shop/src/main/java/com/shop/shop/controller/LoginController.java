package com.shop.shop.controller;

import com.shop.shop.DTO.UserRequest;
import com.shop.shop.service.UserService;
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
    UserService userService;
    @GetMapping  ("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/register")
    String index(@ModelAttribute("userRequest") UserRequest userRequest) {
        userService.registerUser(userRequest);
        return "register";
    }
    @GetMapping(value = "/register")
    public String register(@ModelAttribute("userRequest") UserRequest userRequest) {
        return "register";
    }
}
