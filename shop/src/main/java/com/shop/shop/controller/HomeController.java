package com.shop.shop.controller;

import com.shop.shop.entity.Category;
import com.shop.shop.repository.CategoryRepository;
import com.shop.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import response.ProductDTO;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = {"/"})
    String index(Model model, @Param("search") String search, Pageable pageable) {
        model.addAttribute("search", search);
        Page<ProductDTO> productDTO = productService.search(search,pageable);
        model.addAttribute("products", productDTO);
        return "index";
    }
    @GetMapping(value = {"/product"})
    String listProduct(Model model,@Param("search") String search,Pageable pageable) {
        List<Category> categoryList=categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("search", search);
        Page<ProductDTO> productDTO = productService.search(search,pageable);
        model.addAttribute("products", productDTO);
        return "product-grid";
    }
    @GetMapping(value = {"/product/{categoryId}"})
    String listProductByCategory(Model model,@PathVariable("categoryId") Integer categoryId,Pageable pageable) {
        List<Category> categoryList=categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        Page<ProductDTO> productDTO = productService.searchByCategory(pageable,categoryId);
        model.addAttribute("products", productDTO);
        return "product-grid";
    }

}

