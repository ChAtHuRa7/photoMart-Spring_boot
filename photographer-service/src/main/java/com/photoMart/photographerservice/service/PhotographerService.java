package com.photoMart.photographerservice.service;


import com.photoMart.photographerservice.dto.requestDTO.PhotographerSaveRequestDTO;
import com.photoMart.photographerservice.dto.requestDTO.PhotographerUpdateRequestDto;
import com.photoMart.photographerservice.dto.responseDTO.PhotographerResponseDto;
import com.photoMart.photographerservice.exception.NotFoundException;

import java.util.List;

public interface PhotographerService {
    PhotographerResponseDto addPhotographer(PhotographerSaveRequestDTO photographerSaveRequestDTO);

    PhotographerResponseDto getPhotographer(long photographerId);

    List<PhotographerResponseDto> getPhotographerLimited(int count) throws NotFoundException;

    List<PhotographerResponseDto> getPhotographers();

    PhotographerResponseDto updatePhotographer(long photographerId,
                                               PhotographerUpdateRequestDto photographerUpdateRequestDto);

    PhotographerResponseDto getPhotographerByEmail(String photographerEmail);
}
