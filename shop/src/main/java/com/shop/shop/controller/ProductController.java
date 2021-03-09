package com.shop.shop.controller;

import com.shop.shop.entity.Product;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/product_view")
    public String createProduct(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "/admin/product_view";
    }
    // GET: Hiển thị trang form upload
    @GetMapping("/add_product")
    public String uploadOneFileHandler(Model model) {

        ProductRequest productRequest = new ProductRequest();
        model.addAttribute("productRequest", productRequest);

        return "/add_product";
    }
}
