package com.photomart.packageservice.service.impl;


import com.photomart.packageservice.dto.requestDTO.PackageSaveRequestDTO;
import com.photomart.packageservice.dto.requestDTO.PackageUpdateRequestDto;
import com.photomart.packageservice.dto.responseDTO.PackageResponseDTO;
import com.photomart.packageservice.entity.Package;
import com.photomart.packageservice.exception.NotFoundException;
import com.photomart.packageservice.repository.PackageRepo;
import com.photomart.packageservice.service.PackageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PackageServiceIMPL implements PackageService {

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ModelMapper modelMapper;


    public List<PackageResponseDTO> addPackage(PackageSaveRequestDTO packageSaveRequestDTO) throws Exception{
      if(packageRepo.countByPhotographerId (packageSaveRequestDTO.getPhotographerId())<3){
          Package aPackage = new Package(
                packageSaveRequestDTO.getPhotographerId(),
                  packageSaveRequestDTO.getPackageTittle(),
                  packageSaveRequestDTO.getPackageDescription(),
                  packageSaveRequestDTO.getPrice(),
                  packageSaveRequestDTO.getPackageStatus(),
                  true
          );
          packageRepo.save(aPackage);
          return getPackagesByPhotographerId(aPackage.getPhotographerId());
      }else {
         throw new Exception("Maximum Number of Packages available");
      }
    }

    @Override
    public PackageResponseDTO getPackage(long id) throws  NotFoundException{
        Optional<Package> aPackage = packageRepo.findById(id);
        if(aPackage.isPresent()){
            return modelMapper.map(aPackage.get(),PackageResponseDTO.class);
        }
        else {
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public List<PackageResponseDTO> updatePackage(long id, long photographerId, PackageUpdateRequestDto packageUpdateRequestDto) throws RuntimeException{
        int res = packageRepo.updatePackageByPackageId(packageUpdateRequestDto.getPackageTittle(),
                packageUpdateRequestDto.getPackageDescription(),packageUpdateRequestDto.getPrice(),
                packageUpdateRequestDto.getPackageStatus(),packageUpdateRequestDto.isActiveStatus(),id);

        if(res == 1){
            return getPackagesByPhotographerId(photographerId);
        }
        else {
            throw new RuntimeException("Not Modified");
        }
    }

    @Override
    public List<PackageResponseDTO> getPackagesByPhotographerId(long photographerId) throws NotFoundException{
        List<Package> packageList = packageRepo.findByPhotographerId(photographerId);
        if(!packageList.isEmpty()){
            return packageList.stream().map(aPackage -> modelMapper.map(aPackage,PackageResponseDTO.class)).toList();
        }
        else {
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public List<PackageResponseDTO> deletePackageById(long id, long photographerId) throws NotFoundException {
        try {
            long res = packageRepo.deleteByPackageId(id);
            return getPackagesByPhotographerId(photographerId);
        } catch (NotFoundException e) {
            throw new NotFoundException("Not found");
        }
    }

}
