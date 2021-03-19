package com.shop.shop.repository;

import com.shop.shop.entity.Account;
import com.shop.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByEmail(String email);

    @Query("SELECT p FROM Account p WHERE p.firstName LIKE %?1%"
            + " OR p.email LIKE %?1%")
    Page<Account> search(String search, Pageable pageable);

    Page<Account> getAllBy(Pageable pageable);
}
