package com.photomart.packageservice.dto;


import lombok.*;

public class PackageDTO {
    private long packageId;
    private long photographerId;
    private String packageTittle;
    private String packageDescription;
    private double price;
    private String packageStatus;
    private boolean activeStatus;
}
