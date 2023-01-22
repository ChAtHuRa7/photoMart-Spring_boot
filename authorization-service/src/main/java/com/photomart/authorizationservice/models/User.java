package com.photomart.authorizationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;

    private String userName;

    private String userPassword;

    private String userMail;

    private String mobileNumber;

    private boolean activeState;


}
