package com.photomart.bookingservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "package")
@Setter
@Getter
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long packageId;
    private String packageTittle;
    private String packageDescription;
    private double price;
    private String packageStatus;

    public Package(String packageTittle, String packageDescription, double price, String packageStatus) {
        this.packageTittle = packageTittle;
        this.packageDescription = packageDescription;
        this.price = price;
        this.packageStatus = packageStatus;
    }
}
