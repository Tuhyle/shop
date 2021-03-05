package com.shop.shop.controller;

import com.shop.shop.entity.Product;
import com.shop.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping(value = {"/"})
    String index(final ModelMap model) {
        List<Product> productList=productRepository.findAllBy();
        model.addAttribute("productList", productList);
        return "index";
    }

}

