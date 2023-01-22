package com.photomart.packageservice.dto.requestDTO;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PackageSaveRequestDTO {

    private long photographerId;
    private String packageTittle;
    private String packageDescription;
    private double price;
    private String packageStatus;
}
