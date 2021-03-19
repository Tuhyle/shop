package com.shop.shop.controller;

import com.shop.shop.request.OrderRequest;
import com.shop.shop.service.CartItemService;
import com.shop.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import response.CartItemDTO;
import response.OrderDTO;

/**
 * @author Lê Thị Thúy
 * @created 3/18/2021
 * @project shop
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartItemService cartItemService;
    @GetMapping("/checkout")
    public String add(Model model, Pageable pageable){
        OrderRequest orderRequest=new OrderRequest();
        model.addAttribute("orderRequest", orderRequest);
        Page<CartItemDTO> cartItemDTOS =cartItemService.getAllProductByCart(pageable);
        model.addAttribute("cartItemDTOS",cartItemDTOS);
        return "checkout";
    }
    @PostMapping("/checkout")
    public String createProduct(OrderRequest orderRequest,Model model) {
        model.addAttribute("orderRequest", orderRequest);
        OrderDTO orderDTO=orderService.createOrderByUser(orderRequest);
        return "confirm-checkout";
    }
    @GetMapping("/history")
    public String history(Model model, Pageable pageable){
        return "history-order";
    }
}
