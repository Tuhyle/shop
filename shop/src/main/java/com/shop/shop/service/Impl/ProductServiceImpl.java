package com.shop.shop.service.Impl;

import com.shop.shop.common.ModelMapperUtils;
import com.shop.shop.entity.Category;
import com.shop.shop.entity.PhotoProduct;
import com.shop.shop.entity.Product;
import com.shop.shop.repository.CategoryRepository;
import com.shop.shop.repository.PhotoProductRepository;
import com.shop.shop.repository.ProductRepository;
import com.shop.shop.request.ProductRequest;
import com.shop.shop.service.PhotoProductService;
import com.shop.shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import response.ProductDTO;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final PhotoProductRepository photoProductRepository;

    private final CategoryRepository categoryRepository;

    private final PhotoProductService photoProductService;

    public ProductServiceImpl(ProductRepository productRepository, PhotoProductRepository photoProductRepository, CategoryRepository categoryRepository, PhotoProductService photoProductService) {
        this.productRepository = productRepository;
        this.photoProductRepository = photoProductRepository;
        this.categoryRepository = categoryRepository;
        this.photoProductService = photoProductService;
    }

    private String getFileURL(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download/")
                .path(fileName)
                .toUriString();
    }

    @Override
    public Page<ProductDTO> search(String search, Pageable pageable) {
        try {
            Page<Product> productPage;
            if (search != null) {
                productPage=productRepository.search(search,pageable);
            }else {
                productPage=productRepository.findAllBy(pageable);
            }
            Page<ProductDTO> productDTOS = productPage.map(product -> {
                ProductDTO productDTO = ModelMapperUtils.map(product, ProductDTO.class);
                for (Product item : productPage) {
                    PhotoProduct photoProduct1 = photoProductRepository.findByProductId(item.getId());
                    if(photoProduct1==null){
                        productDTO.setPhoto(getFileURL("default.jpg"));
                    }else {
                        productDTO.setPhoto(getFileURL(photoProduct1.getFileName()));
                    }
                    Optional<Category> category=categoryRepository.findById(item.getCategory().getId());
                    if(!category.isPresent()){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Category does not exist");
                    }
                    productDTO.setCategory(category.get());
                }
                return productDTO;
            });
            log.info("getList product success");
            return productDTOS;
        } catch (Exception e) {
            log.error("getList product fail", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductDTO create(ProductRequest productRequest) {
        try {
            Product product = ModelMapperUtils.map(productRequest, Product.class);
            Product save = productRepository.save(product);
            PhotoProduct photoProduct1 = new PhotoProduct();
            if (productRequest.getPhotos() != null) {
                PhotoProduct photoProduct = PhotoProduct.builder()
                        .fileName(photoProductService.storeFile(productRequest.getPhotos()))
                        .fileNameBlank("default.jpg")
                        .product(save)
                        .build();
                photoProduct1 = photoProductRepository.save(photoProduct);
            }
            ProductDTO productDTO = ModelMapperUtils.map(save, ProductDTO.class);
            productDTO.setPhoto(getFileURL(photoProduct1.getFileName()));
            log.info("function : create product success");
            return productDTO;
        } catch (Exception e) {
            log.info("Create product fail");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not logged in");
        }
    }
}
