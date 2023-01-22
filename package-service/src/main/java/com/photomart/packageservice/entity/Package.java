package com.photomart.packageservice.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "packageService")
@Setter
@Getter
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long packageId;
    private long photographerId;
    private String packageTittle;
    private String packageDescription;
    private double price;
    private String packageStatus;
    private boolean activeStatus;

    public Package(long photographerId, String packageTittle, String packageDescription, double price,
                   String packageStatus, boolean activeStatus) {
        this.photographerId = photographerId;
        this.packageTittle = packageTittle;
        this.packageDescription = packageDescription;
        this.price = price;
        this.packageStatus = packageStatus;
        this.activeStatus = activeStatus;
    }
}
