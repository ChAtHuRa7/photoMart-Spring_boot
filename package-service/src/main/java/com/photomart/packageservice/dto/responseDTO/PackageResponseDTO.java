package com.photomart.packageservice.dto.responseDTO;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PackageResponseDTO {
    private long packageId;
    private long photographerId;
    private String packageTittle;
    private String packageDescription;
    private double price;
    private String packageStatus;
    private boolean activeStatus;
}
