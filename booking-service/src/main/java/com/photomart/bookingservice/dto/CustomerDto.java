package com.photomart.bookingservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private long id;
    private String fullName;
    private String email;
    private int phoneNo;
    private String address;
    private String message;

}
