package com.photomart.authorizationservice.models.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private String jwt;
    private List<String> authorities;
}
