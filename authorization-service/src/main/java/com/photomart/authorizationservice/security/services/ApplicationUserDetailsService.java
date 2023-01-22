package com.photomart.authorizationservice.security.services;

import com.photomart.authorizationservice.models.ApplicationUserDetails;
import com.photomart.authorizationservice.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    UserServices userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new ApplicationUserDetails(userService.getByUserName(username));
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

    }
}
