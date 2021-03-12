package com.shop.shop.repository;

import com.shop.shop.entity.PhotoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoProductRepository extends JpaRepository<PhotoProduct,Integer> {
    PhotoProduct findByProductId(Integer integer);
}
