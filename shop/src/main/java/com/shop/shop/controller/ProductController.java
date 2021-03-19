package com.shop.shop.controller;

import com.shop.shop.entity.PhotoProduct;
import com.shop.shop.entity.Product;
import com.shop.shop.repository.CategoryRepository;
import com.shop.shop.repository.PhotoProductRepository;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.request.ProductRequest;
import com.shop.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import response.ProductDTO;

import java.util.Optional;

@Controller
@RequestMapping(value = "/admin")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PhotoProductRepository photoProductRepository;

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
    public String add(Model model) {
        ProductRequest productRequest = new ProductRequest();
        model.addAttribute("productRequest", productRequest);
        model.addAttribute("category", categoryRepository.findAll());
        return "admin/add_product";
    }
    @GetMapping("/edit_product/{productId}")
    public String get(Model model,@PathVariable("productId") Integer productId) {
        Optional<Product> product=productRepository.findById(productId);
        PhotoProduct productList=photoProductRepository.findAllByProductId(productId);
        ProductRequest productRequest = ProductRequest.builder()
                .name(product.get().getName())
                .metaTitle(product.get().getMetaTitle())
                .summary(product.get().getSummary())
                .type(product.get().getType())
                .price(product.get().getPrice())
                .discount(product.get().getDiscount())
                .quantity(product.get().getQuantity())
                .content(product.get().getContent())
                .categoryId(product.get().getCategory().getId())
                .build();
        productRequest.setId(productId);
        model.addAttribute("productRequest", productRequest);
        model.addAttribute("category", categoryRepository.findAll());
        return "/admin/edit_product";
    }
    @PostMapping("/edit_product")
    public String edit(Model model) {
        ProductRequest productRequest = new ProductRequest();
        model.addAttribute("productRequest", productRequest);
        model.addAttribute("category", categoryRepository.findAll());
        productService.edit(productRequest,productRequest.getId());
        return "redirect:/admin/product_view";
    }

    @PostMapping("/add_product")
    public String createProduct(ProductRequest productRequest) {
        ProductDTO productDTO=productService.create(productRequest);
        return "redirect:/admin/product_view";
    }
}
