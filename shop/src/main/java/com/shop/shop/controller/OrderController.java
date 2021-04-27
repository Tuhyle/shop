package com.shop.shop.controller;

import com.shop.shop.entity.Account;
import com.shop.shop.entity.Order;
import com.shop.shop.entity.OrderItem;
import com.shop.shop.entity.Product;
import com.shop.shop.repository.AccountRepository;
import com.shop.shop.repository.OrderItemRepository;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.request.OrderRequest;
import com.shop.shop.service.CartItemService;
import com.shop.shop.service.OrderService;
import com.shop.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import response.CartItemDTO;
import response.OrderDTO;
import response.ProductDTO;

import java.util.List;

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

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductService productService;

    @GetMapping("/checkout")
    public String add(Model model, Pageable pageable) {
        OrderRequest orderRequest = new OrderRequest();
        model.addAttribute("orderRequest", orderRequest);
        Page<CartItemDTO> cartItemDTOS = cartItemService.getAllProductByCart(pageable);
        model.addAttribute("cartItemDTOS", cartItemDTOS);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String createProduct(Model model, Pageable pageable,OrderRequest orderRequest) {
        OrderDTO orderDTO = orderService.createOrderByUser(orderRequest);
        return "redirect:/order/history";
    }
    @GetMapping("/history_user/{status}")
    public String historyByUser(Model model, Pageable pageable,@PathVariable("status") int status) {
        Page<OrderDTO> orderDTOS = orderService.getAllByStatus(status, pageable);
        Page<ProductDTO> productDTOS = productService.findByUser(status,pageable);
        model.addAttribute("orderDTOS", orderDTOS);
        model.addAttribute("productList", productDTOS);
        return "history-order";
    }
    @GetMapping("/history")
    public String history(Model model, Pageable pageable) {
        Page<OrderDTO> orderDTOS = orderService.getAllByUser(pageable);
        model.addAttribute("orderDTOS", orderDTOS);

        Page<ProductDTO> productDTO0 = productService.findByUser(0,pageable);
        model.addAttribute("productDTO0", productDTO0);

        Page<ProductDTO> productDTO1 = productService.findByUser(1,pageable);
        model.addAttribute("productDTO1", productDTO1);

        Page<ProductDTO> productDTOS2 = productService.findByUser(2,pageable);
        model.addAttribute("productDTOS2", productDTOS2);

        Page<ProductDTO> productDTOS3 = productService.findByUser(3,pageable);
        model.addAttribute("productDTOS3", productDTOS3);

        Page<ProductDTO> productDTOS4 = productService.findByUser(4,pageable);
        model.addAttribute("productDTOS4", productDTOS4);
        return "history-order";
    }
}
