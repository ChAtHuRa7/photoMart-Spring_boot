package com.photomart.packageservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageUpdateRequestDto {
    private String packageTittle;
    private String packageDescription;
    private double price;
    private String packageStatus;
    private boolean activeStatus;

}
