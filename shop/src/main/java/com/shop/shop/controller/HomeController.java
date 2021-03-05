package com.shop.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping(value = {"/"})
    String index(final ModelMap model, final HttpServletRequest request, HttpSession session, final HttpServletResponse response) {
        return "index";
    }

}

