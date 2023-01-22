package com.photomart.apigateway.models.respons;

import com.photomart.apigateway.models.Authority;
import lombok.*;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtValidationResponse {
    private String userName;
    private String jwt;
    private List<Authority> authorities;
}
