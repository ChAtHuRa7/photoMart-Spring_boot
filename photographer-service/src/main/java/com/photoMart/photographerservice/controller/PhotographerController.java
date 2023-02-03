package com.photoMart.photographerservice.controller;

import com.photoMart.photographerservice.dto.requestDTO.PhotographerSaveRequestDTO;
import com.photoMart.photographerservice.dto.requestDTO.PhotographerUpdateRequestDto;
import com.photoMart.photographerservice.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/photographers")
public class PhotographerController {

    @Autowired
    private PhotographerService photographerService;

    @PostMapping(path = "/")
    public ResponseEntity<?> createPhotographer(@RequestBody PhotographerSaveRequestDTO photographerSaveRequestDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(photographerService.addPhotographer(photographerSaveRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e);
        }

    }
    @GetMapping(path = "/" , params = "pId")
    public ResponseEntity<?> getPhotographer(@RequestParam("pId")long photographerId) {
        try {
            return ResponseEntity.ok(photographerService.getPhotographer(photographerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping(path = "/" , params = "count")
    public ResponseEntity<?> getPhotographerLimited(@RequestParam("count")int count) {
        try {
            return ResponseEntity.ok(photographerService.getPhotographerLimited(count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping(path = "/" , params = "pEmail")
    public ResponseEntity<?> getPhotographerLimited(@RequestParam("pEmail")String photographerEmail) {
        try {
            return ResponseEntity.ok(photographerService.getPhotographerByEmail(photographerEmail));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    @GetMapping(path = "/")
    public ResponseEntity<?> getPhotographers() {
        try {
            return ResponseEntity.ok(photographerService.getPhotographers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PutMapping(path = "/")
    public ResponseEntity<?> updatePhotographer(@RequestParam("pId") long photographerId,
                                @RequestBody PhotographerUpdateRequestDto photographerUpdateRequestDto){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(photographerService.updatePhotographer(photographerId,photographerUpdateRequestDto));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }

    }


}
