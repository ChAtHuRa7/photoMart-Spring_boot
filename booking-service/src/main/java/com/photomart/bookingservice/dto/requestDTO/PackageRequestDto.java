package com.photomart.bookingservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageRequestDto {
    private String packageTittle;
    private String packageDescription;
    private double price;
    private String packageStatus;
}
