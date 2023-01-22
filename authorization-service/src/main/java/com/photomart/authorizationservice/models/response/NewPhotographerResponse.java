package com.photomart.authorizationservice.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPhotographerResponse{

    private long photographerId;
    private String photographerName;
    private String photographerEmail;
    private String photographerMobileNo;
    private String studioName;
    private String studioEmail;
    private String description;
    private String profilePicLink;
    private String whatsAppNumber;
    private String contactEmail;
    private String faceBookProfile;
    private boolean isEnable;

}
