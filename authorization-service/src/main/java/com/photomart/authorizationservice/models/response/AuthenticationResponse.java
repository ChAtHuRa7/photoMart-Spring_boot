package com.photomart.authorizationservice.models.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private String jwt;
}
