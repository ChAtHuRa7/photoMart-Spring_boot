package com.photomart.packageservice.service;


import com.photomart.packageservice.dto.requestDTO.PackageSaveRequestDTO;
import com.photomart.packageservice.dto.requestDTO.PackageUpdateRequestDto;
import com.photomart.packageservice.dto.responseDTO.PackageResponseDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PackageService {

    List<PackageResponseDTO> addPackage(PackageSaveRequestDTO packageSaveRequestDTO) throws Exception;
    PackageResponseDTO getPackage(long id);
    List<PackageResponseDTO> updatePackage(long id,long photographerId ,PackageUpdateRequestDto packageUpdateRequestDto);

    List<PackageResponseDTO> getPackagesByPhotographerId(long photographerId);

    List<PackageResponseDTO> deletePackageById(long id, long photographerId);
}
