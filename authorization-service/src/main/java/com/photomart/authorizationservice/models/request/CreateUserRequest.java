package com.photomart.authorizationservice.models.request;

import com.photomart.authorizationservice.entity.Authorities;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @Email
    private String userEmail;
    private String userName;
    private String password;
    private String mobileNumber;
    private String studioName;
    private String address;
    private List<String> authorities;

}
