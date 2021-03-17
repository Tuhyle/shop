package com.shop.shop.repository;

import com.shop.shop.entity.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Page<CartItem> findByCartId(Integer cartId, Pageable pageable);

    CartItem findByCartIdAndProductId(Integer cartId, Integer productId);

}
