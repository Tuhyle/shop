package com.shop.shop.repository;

import com.shop.shop.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findAllByCreateAt(Date date);


    @Query(value = "SELECT * FROM order_item WHERE DATE_FORMAT(created_at,'%Y-%m-%d')", nativeQuery = true)
    List<OrderItem> findAllByCreateAt_Date(String date);

    @Query(value = "SELECT * FROM order_item WHERE YEAR(created_at) = :year", nativeQuery = true)
    List<OrderItem> findAllByCreateAtYear(Integer year);

    List<OrderItem> findAllByOrderId(Integer Id);
}
