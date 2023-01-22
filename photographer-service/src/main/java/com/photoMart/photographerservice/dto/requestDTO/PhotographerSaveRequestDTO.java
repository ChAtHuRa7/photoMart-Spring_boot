package com.photoMart.photographerservice.dto.requestDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotographerSaveRequestDTO {
    private String userEmail;
    private String photographerName;
    private String mobileNumber;
    private String studioName;
    private String address;


}
