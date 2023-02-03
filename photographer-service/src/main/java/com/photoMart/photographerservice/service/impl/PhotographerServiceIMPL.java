package com.photoMart.photographerservice.service.impl;



import com.photoMart.photographerservice.dto.requestDTO.PhotographerSaveRequestDTO;
import com.photoMart.photographerservice.dto.requestDTO.PhotographerUpdateRequestDto;
import com.photoMart.photographerservice.dto.responseDTO.PhotographerResponseDto;
import com.photoMart.photographerservice.entity.Photographer;
import com.photoMart.photographerservice.exception.NotFoundException;
import com.photoMart.photographerservice.repository.PhotographerRepo;
import com.photoMart.photographerservice.service.PhotographerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PhotographerServiceIMPL implements PhotographerService {

    @Autowired
    private PhotographerRepo photographerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PhotographerResponseDto addPhotographer(PhotographerSaveRequestDTO photographerSaveRequestDTO)
            throws RuntimeException {

      if (!photographerRepo.existsByPhotographerEmailEqualsIgnoreCase(photographerSaveRequestDTO.getUserEmail())) {
          Photographer photographer = new Photographer(
                  photographerSaveRequestDTO.getPhotographerName(),
                  photographerSaveRequestDTO.getUserEmail(),
                  photographerSaveRequestDTO.getMobileNumber(),
                  photographerSaveRequestDTO.getAddress(),
                  photographerSaveRequestDTO.getStudioName(),
                  "",
                  "",
                  "",
                  "",
                  "",
                  "",
                  true
          );
          photographerRepo.save(photographer);
        return getPhotographerByEmail(photographer.getPhotographerEmail());
      }else {
          throw new RuntimeException("User already exists");
      }
    }

    @Override
    public PhotographerResponseDto getPhotographer(long photographerId) throws NotFoundException {
        Optional<Photographer> photographer = photographerRepo.findById(photographerId);
        if (photographer.isPresent() && photographer.get().isEnable()){
            return modelMapper.map(photographer.get(),PhotographerResponseDto.class);
        }else {
            throw new NotFoundException("Not Found Photographer");
        }
       // return null;
    }

    @Override
    public List<PhotographerResponseDto> getPhotographerLimited(int count) throws NotFoundException {
        Pageable num = PageRequest.ofSize(count);
        List<Photographer> photographers = photographerRepo.getPhotographersInLimite(num);
        if(!photographers.isEmpty()){
            return photographers.stream().map(photographer ->
                    modelMapper.map(photographer,PhotographerResponseDto.class)
            ).toList();
        }
        else {
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public List<PhotographerResponseDto> getPhotographers() throws NotFoundException{
        List<Photographer> photographers = photographerRepo.findByIsEnableTrue();
        if(!photographers.isEmpty()){
            return photographers.stream().map(photographer ->
                    modelMapper.map(photographer,PhotographerResponseDto.class)
            ).toList();
        }
        else {
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public PhotographerResponseDto updatePhotographer(long photographerId,
                                                      PhotographerUpdateRequestDto photographerUpdateRequestDto) {
       int res = photographerRepo.updatePhotographerByIdEquals(
                photographerUpdateRequestDto.getPhotographerMobileNo(),
               photographerUpdateRequestDto.getStudioName(),
               photographerUpdateRequestDto.getStudioEmail(),
               photographerUpdateRequestDto.getDescription(),
               photographerUpdateRequestDto.getProfilePicLink(),
               photographerUpdateRequestDto.getWhatsAppNumber(),
               photographerUpdateRequestDto.getContactEmail(),
               photographerUpdateRequestDto.getFaceBookProfile(),
               photographerUpdateRequestDto.getAddress(),
               photographerId
        );

       return getPhotographer(photographerId);
    }

    @Override
    public PhotographerResponseDto getPhotographerByEmail(String photographerEmail) throws NotFoundException{
        Optional<Photographer> photographer = photographerRepo.findByPhotographerEmailEqualsIgnoreCase(photographerEmail);
        if(photographer.isPresent() && photographer.get().isEnable()){
            return modelMapper.map(photographer.get(),PhotographerResponseDto.class);
        }
        else{
            throw new NotFoundException("Not found");
        }
    }
}
