package com.photoMart.userservice.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private long userId;
    private String userName;
    private String userMail;
    private String mobileNumber;
    private String address;
    private boolean activeState;
}
