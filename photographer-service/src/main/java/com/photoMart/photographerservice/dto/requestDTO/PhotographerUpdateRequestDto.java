package com.photoMart.photographerservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotographerUpdateRequestDto {
    private String photographerMobileNo;
    private String studioName;
    private String studioEmail;
    private String description;
    private String profilePicLink;
    private String whatsAppNumber;
    private String contactEmail;
    private String faceBookProfile;
}
