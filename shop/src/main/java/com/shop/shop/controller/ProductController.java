package com.shop.shop.controller;

import com.shop.shop.repository.CategoryRepository;
import com.shop.shop.request.ProductRequest;
import com.shop.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import response.ProductDTO;

@Controller
@RequestMapping(value = "/admin")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/product_view")
    public String createProduct(Model model, @Param("search") String search, Pageable pageable) {
        model.addAttribute("search", search);
        Page<ProductDTO> productDTO = productService.search(search,pageable);
        model.addAttribute("products", productDTO);
        return "admin/product_view";
    }
    @GetMapping("/add_product")
    public String getCreate(Model model) {

        ProductRequest productRequest = new ProductRequest();
        model.addAttribute("productRequest", productRequest);
        model.addAttribute("category", categoryRepository.findAll());
        return "admin/add_product";
    }

    @PostMapping("/add_product")
    public String createProduct(ProductRequest productRequest) {
        productService.create(productRequest);
        return "admin/add_product";
    }
}
