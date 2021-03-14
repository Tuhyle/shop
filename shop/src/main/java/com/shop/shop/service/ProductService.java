package com.shop.shop.service;

import com.shop.shop.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import response.ProductDTO;

public interface ProductService {
    Page<ProductDTO> search(String search, Pageable pageable);
    ProductDTO create(ProductRequest productRequest);
    Page<ProductDTO> searchByCategory(Pageable pageable, Integer categoryId);
}
