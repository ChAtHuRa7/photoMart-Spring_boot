package com.photoMart.userservice.controller;



import com.photoMart.userservice.dto.requestDTO.UserSaveRequestDTO;
import com.photoMart.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.photoMart.userservice.exception.NotFoundException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping(path = "/")
    public ResponseEntity<?> saveUser(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {
       try {
           return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userSaveRequestDTO));
       } catch (Exception e) {
           return  ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
       }
    }

    @GetMapping(path = "/", params = "uId")
    public ResponseEntity<?> getUserById(@RequestParam("uId") long userId) {
        try {
            return ResponseEntity.ok(userService.getUserById(userId));
        } catch (NotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/", params = "uEmail")
    public ResponseEntity<?> getUserByEmail(@RequestParam("uEmail") String userEmail) {
        try {
            return ResponseEntity.ok(userService.getUserByEmail(userEmail));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
