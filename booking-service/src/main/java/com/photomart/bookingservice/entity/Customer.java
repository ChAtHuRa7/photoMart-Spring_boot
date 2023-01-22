package com.photomart.bookingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String email;
    private int phoneNo;
    private String address;
    private String message;

    public Customer(String fullName, String email, int phoneNo, String address, String message) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.message = message;
    }
}
