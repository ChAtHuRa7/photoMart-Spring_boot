package com.photomart.authorizationservice.services;

import com.photomart.authorizationservice.Dto.UserDto;
import com.photomart.authorizationservice.entity.Users;
import com.photomart.authorizationservice.models.User;
import com.photomart.authorizationservice.models.request.CreateUserRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserServices {
    Users getUser(String userEmail) throws UsernameNotFoundException;

    UserDto getByUserName(String userName) throws UsernameNotFoundException;

    String createNewUser(CreateUserRequest createUserRequest) throws Exception;
}
