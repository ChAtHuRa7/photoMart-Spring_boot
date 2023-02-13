package com.photomart.authorizationservice.services.impl;


import com.photomart.authorizationservice.Dto.UserDto;
import com.photomart.authorizationservice.entity.Authorities;
import com.photomart.authorizationservice.entity.Users;
import com.photomart.authorizationservice.models.ApplicationUserDetails;
import com.photomart.authorizationservice.models.request.CreateNewPhotographerRequest;
import com.photomart.authorizationservice.models.request.CreateNewUserRequest;
import com.photomart.authorizationservice.models.request.CreateUserRequest;
import com.photomart.authorizationservice.models.response.NewPhotographerResponse;
import com.photomart.authorizationservice.models.response.NewUserResponse;
import com.photomart.authorizationservice.repository.UsersRepo;
import com.photomart.authorizationservice.services.UserServices;
import com.photomart.authorizationservice.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    @Transactional
    public Users getUser(String userEmail) throws UsernameNotFoundException{
        return usersRepo.findByUserEmailIgnoreCase(userEmail);
    }

    @Override
    @Transactional
    public UserDto getByUserName(String userEmail) throws UsernameNotFoundException{

        System.out.println("### 1 ###");

        Users users = usersRepo.findByUserEmailIgnoreCase(userEmail);

        System.out.println("### 2 ###");

        System.out.println(users.getUserEmail());
        System.out.println(users);

        System.out.println("### 3 ###");


        if(users != null){
           UserDto userDto =  new UserDto(
                   users.getId(),
                   users.getUserEmail(),
                   users.getPassword(),
                   users.isAccountNonExpired(),
                   users.isAccountNonLocked(),
                   users.isCredentialsNonExpired(),
                   users.isEnabled()
           );


           userDto.setAuthorities(users.getAuthorities().stream()
                   .map(Authorities::getAuthorities).collect(Collectors.toList()));
           return userDto;
       }
       else {
           throw new UsernameNotFoundException("User Not found");
       }
    }

    @Override
    public String createNewUser(CreateUserRequest createUserRequest) throws Exception{
        if(!usersRepo.existsByUserEmailEqualsIgnoreCase(createUserRequest.getUserEmail())){
            Users users = new Users(
                    createUserRequest.getUserEmail(),
                    passwordEncoder.encode(createUserRequest.getPassword()),
                    true,
                    true,
                    true,
                    true
            );

            List<Authorities> authorities = createUserRequest.getAuthorities()
                    .stream()
                    .map(Authorities::new).toList();

            users.setAuthorities(authorities);
            usersRepo.save(users);

            //TODO : complete web client //

            authorities.forEach(auth -> {

                if (auth.getAuthorities().equalsIgnoreCase("PHOTOGRAPHER")) {
                    CreateNewPhotographerRequest createNewPhotographerRequest= new CreateNewPhotographerRequest(
                            createUserRequest.getUserEmail(),
                            createUserRequest.getUserName(),
                            createUserRequest.getMobileNumber(),
                            createUserRequest.getStudioName(),
                            createUserRequest.getAddress()

                    );
                    webClientBuilder.build()
                            .post()
                            .uri("lb://photographer-service/api/v1/photographers/")
                            .header("Authorities",",SERVICE")
                            .header("Authorization","token")
                            .accept(MediaType.APPLICATION_JSON)
                            .body(Mono.just(createNewPhotographerRequest), CreateNewPhotographerRequest.class)
                            .retrieve()
                            .bodyToMono(NewPhotographerResponse.class)
                            .block();

                } else if (auth.getAuthorities().equalsIgnoreCase("USER")) {
                    CreateNewUserRequest createNewUserRequest = new CreateNewUserRequest(
                            createUserRequest.getUserEmail(),
                            createUserRequest.getUserName(),
                            createUserRequest.getMobileNumber(),
                            createUserRequest.getAddress()

                    );

                    webClientBuilder.build()
                            .post()
                            .uri("lb://user-service/api/v1/users/")
                            .header("Authorities",",SERVICE")
                            .header("Authorization","token")
                            .accept(MediaType.APPLICATION_JSON)
                            .body(Mono.just(createNewUserRequest), CreateNewUserRequest.class)
                            .retrieve()
                            .bodyToMono(NewUserResponse.class)
                            .block();
                }
            });

        }
        else {
            throw new Exception("User already exist");
        }

        UserDto userDto = getByUserName(createUserRequest.getUserEmail());

        return jwtUtil.createToken(new ApplicationUserDetails(userDto));
    }

}


