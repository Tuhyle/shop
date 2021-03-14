package com.shop.shop.controller;

import com.shop.shop.request.AddCartRequest;
import com.shop.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lê Thị Thúy
 * @created 3/13/2021
 * @project shop
 */
@Controller
@RequestMapping(value = "/cart")
public  class CartController {
    @Autowired
    CartService cartService;
    @GetMapping(value = "/add/{productId}")
    public String getCreate(@PathVariable("productId") Integer productId) {
        AddCartRequest addCartRequest = new AddCartRequest();
        addCartRequest.setProductId(productId);
        cartService.addToCart(addCartRequest);
        return "redirect:/";
    }
}
