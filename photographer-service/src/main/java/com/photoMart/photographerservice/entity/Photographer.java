package com.photoMart.photographerservice.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "PhotographerService")
@Getter
@Setter
@NoArgsConstructor
public class Photographer {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
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

        public Photographer(String photographerName, String photographerEmail, String photographerMobileNo,
                            String studioName, String studioEmail, String description, String profilePicLink,
                            String whatsAppNumber, String contactEmail, String faceBookProfile, boolean isEnable) {
                this.photographerName = photographerName;
                this.photographerEmail = photographerEmail;
                this.photographerMobileNo = photographerMobileNo;
                this.studioName = studioName;
                this.studioEmail = studioEmail;
                this.description = description;
                this.profilePicLink = profilePicLink;
                this.whatsAppNumber = whatsAppNumber;
                this.contactEmail = contactEmail;
                this.faceBookProfile = faceBookProfile;
                this.isEnable = isEnable;
        }


}
