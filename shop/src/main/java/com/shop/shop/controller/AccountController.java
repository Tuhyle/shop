package com.shop.shop.controller;

import com.shop.shop.entity.Account;
import com.shop.shop.repository.AccountRepository;
import com.shop.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import response.AccountDTO;
import response.ProductDTO;

import java.util.Optional;

/**
 * @author Lê Thị Thúy
 * @created 3/22/2021
 * @project shop
 */
@Controller
@RequestMapping(value = "/admin")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/account")
    public String getALl(Model model, @Param("search") String search, Pageable pageable) {
        model.addAttribute("search", search);
        Page<AccountDTO> accountDTOS = accountService.getAll(search, pageable);
        model.addAttribute("accounts", accountDTOS);
        return "admin/acount";
    }
    @GetMapping("/edit/{accountId}")
    public String edit(Model model, @PathVariable("accountId") Integer accountId) {
        Optional<Account> account=accountRepository.findById(accountId);
        return "admin/acount";
    }
}
