package com.shop.shop.controller;

import com.shop.shop.common.UserValidator;
import com.shop.shop.request.UserCreateRequest;
import com.shop.shop.service.AccountService;
import com.shop.shop.service.userDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    @Autowired
    AccountService userService;

    @Autowired
    userDetailsServiceImpl detailsService;

    @Autowired
    private UserValidator userValidator;
    @GetMapping  ("/login")
    public String login(Model model, String error, String logout) {
        if (detailsService.isAuthenticated()) {
            return "redirect:/home";
        }
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }

    @GetMapping  ("/403")
    public String access() {
        return "403";
    }
    @PostMapping("/register")
    String index(@ModelAttribute("userRequest") UserCreateRequest userRequest, BindingResult bindingResult) {
        userValidator.validate(userRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/register";
        }
        userService.registerUser(userRequest);
        return "login";
    }
    @GetMapping(value = "/register")
    public String register(@ModelAttribute("userRequest") UserCreateRequest userRequest) {
        return "register";
    }
}
