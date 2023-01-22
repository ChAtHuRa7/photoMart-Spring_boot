package com.photoMart.photographerservice.service;


import com.photoMart.photographerservice.dto.requestDTO.PhotographerSaveRequestDTO;
import com.photoMart.photographerservice.dto.requestDTO.PhotographerUpdateRequestDto;
import com.photoMart.photographerservice.dto.responseDTO.PhotographerResponseDto;

import java.util.List;

public interface PhotographerService {
    PhotographerResponseDto addPhotographer(PhotographerSaveRequestDTO photographerSaveRequestDTO);

    PhotographerResponseDto getPhotographer(long photographerId);

    List<PhotographerResponseDto> getPhotographers();

    PhotographerResponseDto updatePhotographer(long photographerId,
                                               PhotographerUpdateRequestDto photographerUpdateRequestDto);
}
