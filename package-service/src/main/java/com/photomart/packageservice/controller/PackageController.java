package com.photomart.packageservice.controller;

import com.photomart.packageservice.dto.requestDTO.PackageSaveRequestDTO;
import com.photomart.packageservice.dto.requestDTO.PackageUpdateRequestDto;
import com.photomart.packageservice.exception.NotFoundException;
import com.photomart.packageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping(path = "/")
    public ResponseEntity<?> addPackage(@RequestBody PackageSaveRequestDTO packageSaveRequestDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(packageService.addPackage(packageSaveRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }

    @GetMapping(path = "/", params = "id")
    public ResponseEntity<?> getPackage(@RequestParam("id")long packageId){
        try {
            return ResponseEntity.ok(packageService.getPackage(packageId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/", params = "pId")
    public ResponseEntity<?> getPackages(@RequestParam("pId")long photographerId){
        try {
            return ResponseEntity.ok(packageService.getPackagesByPhotographerId(photographerId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(path = "/", params = {"id","pId"})
    public ResponseEntity<?> getPackages(@RequestParam("id")long id,@RequestParam("pId")long photographerId,
                                         @RequestBody PackageUpdateRequestDto packageUpdateRequestDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(packageService.updatePackage(id,photographerId,packageUpdateRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/", params = {"id","pId"})
    public ResponseEntity<?> getPackages(@RequestParam("id")long id,@RequestParam("pId")long photographerId){
        try {
            return ResponseEntity.ok(packageService.deletePackageById(id,photographerId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
