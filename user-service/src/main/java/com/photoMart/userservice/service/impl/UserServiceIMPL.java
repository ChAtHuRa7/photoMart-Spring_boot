package com.photoMart.userservice.service.impl;


import com.photoMart.userservice.dto.requestDTO.UserSaveRequestDTO;
import com.photoMart.userservice.dto.responseDTO.UserResponseDTO;
import com.photoMart.userservice.entity.User;
import com.photoMart.userservice.exception.NotFoundException;
import com.photoMart.userservice.repository.UserRepo;
import com.photoMart.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDTO addUser(UserSaveRequestDTO userSaveRequestDTO) throws NotFoundException, Exception{
        if (!userRepo.existsByUserMailIgnoreCase(userSaveRequestDTO.getUserEmail())) {
            User user = new User(
                    userSaveRequestDTO.getUserName(),
                    userSaveRequestDTO.getUserEmail(),
                    userSaveRequestDTO.getMobileNumber(),
                    userSaveRequestDTO.getAddress(),
                    true
            );
            User newUser = userRepo.save(user);
            return modelMapper.map(newUser,UserResponseDTO.class);
        }else {
            throw new Exception("User Already Exist");
        }
    }

    @Override
    public UserResponseDTO getUserByEmail(String userEmail) throws NotFoundException{
      User user = userRepo.findByUserMailIgnoreCase(userEmail);
      if(user != null){
          return modelMapper.map(user,UserResponseDTO.class);
      }
      else {
          throw new NotFoundException("User Not found");
      }
    }

    @Override
    public UserResponseDTO getUserById(long userId) throws NotFoundException{
        User user = userRepo.findByUserIdEquals(userId);
        if(user != null){
            return modelMapper.map(user,UserResponseDTO.class);
        }
        else {
            throw new NotFoundException("User Not found");
        }
    }
}
