package com.photoMart.userservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequestDTO {
    private String userEmail;
    private String userName;
    private String mobileNumber;
    private String address;

}
