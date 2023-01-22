package com.photomart.authorizationservice.models;

import com.photomart.authorizationservice.Dto.UserDto;
import com.photomart.authorizationservice.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApplicationUserDetails implements UserDetails {

        private final UserDto user;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {

                return user.getAuthorities().stream().map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
//
//                return user.getAuthorities().stream().map(authorities ->
//                (GrantedAuthority) () -> String.valueOf(authorities.getAuthorities()))
//                .collect(Collectors.toList());
        }

        @Override
        public String getPassword() {
                return user.getPassword();
        }

        @Override
        public String getUsername() {
                return user.getUserEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
                return user.isAccountNonExpired();
        }

        @Override
        public boolean isAccountNonLocked() {
                return user.isAccountNonLocked();
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return user.isCredentialsNonExpired();
        }

        @Override
        public boolean isEnabled() {
                return user.isEnabled();
        }
}
