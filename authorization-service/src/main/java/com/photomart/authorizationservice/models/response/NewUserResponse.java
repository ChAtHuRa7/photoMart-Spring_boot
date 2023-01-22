package com.photomart.authorizationservice.models.response;

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
public class NewUserResponse{

    private long userId;
    private String userName;
    private String userMail;
    private String mobileNumber;
    private String address;
    private boolean activeState;

}
