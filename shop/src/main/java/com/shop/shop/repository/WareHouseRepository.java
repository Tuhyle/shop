package com.shop.shop.repository;

import com.shop.shop.entity.WareHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHome,Integer> {
}
