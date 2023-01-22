package com.photoMart.userservice.service;


import com.photoMart.userservice.dto.requestDTO.UserSaveRequestDTO;
import com.photoMart.userservice.dto.responseDTO.UserResponseDTO;
import com.photoMart.userservice.exception.NotFoundException;

public interface UserService {
    UserResponseDTO addUser(UserSaveRequestDTO userSaveRequestDTO) throws NotFoundException, Exception;

    UserResponseDTO getUserByEmail(String userEmail) throws NotFoundException;

    UserResponseDTO getUserById(long userId) throws NotFoundException;
}
