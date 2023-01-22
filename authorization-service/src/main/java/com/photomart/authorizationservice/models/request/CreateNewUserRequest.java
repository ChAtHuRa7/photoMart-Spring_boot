package com.photomart.authorizationservice.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewUserRequest {
    private String userEmail;
    private String userName;
    private String mobileNumber;
    private String address;

}
