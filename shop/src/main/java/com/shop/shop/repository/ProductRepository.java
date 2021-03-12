package com.shop.shop.repository;

import com.shop.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllBy();

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
            + " OR p.name LIKE %?1%"
            + " OR p.metaTitle LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%")
    Page<Product> search(String keyword, Pageable pageable);

    Page<Product> findAllBy(Pageable pageable);
}
