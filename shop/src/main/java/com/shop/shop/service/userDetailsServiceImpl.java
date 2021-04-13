package com.shop.shop.service;

import com.shop.shop.entity.Account;
import com.shop.shop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
@Service
public class userDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(userDetailsServiceImpl.class);

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = userRepository.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + email + " was not found in the database");
        }

        // EMPLOYEE,MANAGER,..
        String role = account.getUserRole();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        // ROLE_EMPLOYEE, ROLE_MANAGER
        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        grantList.add(authority);

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(account.getEmail(), //
                account.getPassword(), true, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);
        return userDetails;
    }
}
