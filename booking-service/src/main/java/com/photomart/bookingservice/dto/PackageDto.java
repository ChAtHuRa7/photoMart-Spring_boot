package com.photomart.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageDto {
    private long packageId;
    private String packageTittle;
    private String packageDescription;
    private double price;
    private String packageStatus;
}
