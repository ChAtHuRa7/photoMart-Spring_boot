package com.photoMart.photographerservice.dto.responseDTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhotographerCardResponseDTO {

    private String p_name;

    private String StudioName;

    private String description;

    private String profilePicLink;
}
