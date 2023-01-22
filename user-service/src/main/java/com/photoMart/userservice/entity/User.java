package com.photoMart.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "UserService")

@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String userName;
    private String userMail;
    private String mobileNumber;
    private String address;
    private boolean activeState;

    public User(String userName, String userMail, String mobileNumber, String address, boolean activeState) {
        this.userName = userName;
        this.userMail = userMail;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.activeState = activeState;
    }
}
