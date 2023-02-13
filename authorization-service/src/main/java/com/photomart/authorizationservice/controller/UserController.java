package com.photomart.authorizationservice.controller;

import com.photomart.authorizationservice.models.request.CreateUserRequest;
import com.photomart.authorizationservice.models.response.AuthenticationResponse;
import com.photomart.authorizationservice.models.response.JwtValidationResponse;
import com.photomart.authorizationservice.security.services.ApplicationUserDetailsService;
import com.photomart.authorizationservice.services.UserServices;
import com.photomart.authorizationservice.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO : block api from external access//

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;
    @Autowired
    private UserServices userServices;


    @GetMapping(path = "/")
    public ResponseEntity<?> validateToken(HttpServletRequest httpServletRequest){
        String userName = (String) httpServletRequest.getAttribute("userName");
        String token = (String) httpServletRequest.getAttribute("jwt");
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) httpServletRequest.getAttribute("authorities");

        try{
            System.out.println("@@ 1 @@");
            return ResponseEntity.ok(JwtValidationResponse.builder().userEmail(userName).jwt(token).authorities(authorities).build());
        } catch (Exception e) {
            System.out.println("@@ 2 @@");
            System.out.println("@@" + e.getMessage() + "@@");
            System.out.println("@@" + e + "@@");
            throw new RuntimeException(e);
        }
//        return ResponseEntity.ok(JwtValidationResponse.builder().userEmail(userName).jwt(token).authorities(authorities).build());
    }

    @PostMapping(path = "/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        String token;
        try {
            token = userServices.createNewUser(createUserRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
        return ResponseEntity.ok(new AuthenticationResponse(token,createUserRequest.getAuthorities()));
    }



}
