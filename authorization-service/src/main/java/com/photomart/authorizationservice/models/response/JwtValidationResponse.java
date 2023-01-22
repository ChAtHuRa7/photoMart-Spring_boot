package com.photomart.authorizationservice.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@Builder
public class JwtValidationResponse {
    private String userEmail;
    private String jwt;
    private List<GrantedAuthority> authorities;
}
