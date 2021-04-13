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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
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
    public String getALl(Model model, @Param("search") String search, Pageable pageable,Integer id,String userRole) {
        model.addAttribute("search", search);
        Page<AccountDTO> accountDTOS = accountService.getAll(search, pageable);
        model.addAttribute("accounts", accountDTOS);
        model.addAttribute("id",id);
        model.addAttribute("userRole",userRole);
        return "admin/account";
    }
    @PostMapping("/edit-role")
    public String editRole(Model model,Integer id,String userRole) {
        Optional<Account> account=accountRepository.findById(id);
        account.get().setUserRole(userRole);
        accountRepository.save(account.get());
        return "admin/account";
    }
    @PostMapping("/change-role")
    public Boolean changeRole(Model model, Integer accountId,String role) {
        model.addAttribute("accountId",accountId);
        model.addAttribute("role",role);
        Optional<Account> account=accountRepository.findById(accountId);
        account.get().setUserRole(role);
        accountRepository.save(account.get());
        return true;
    }
}
