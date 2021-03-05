package com.shop.shop.service;

import com.shop.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class userDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.shop.shop.entity.User user = userRepository.findByEmail(email);
        System.out.println("Account= " + user);

        if (user == null) {
            throw new UsernameNotFoundException("User " //
                    + email + " was not found in the database");
        }

        // EMPLOYEE,MANAGER,..
        String role = user.getAdmin();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        // ROLE_EMPLOYEE, ROLE_MANAGER
        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        grantList.add(authority);


        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(user.getPasswordHash(),
                user.getEmail(), accountNonExpired,
                 accountNonLocked, credentialsNonExpired,true,grantList);

        return userDetails;
    }
}
