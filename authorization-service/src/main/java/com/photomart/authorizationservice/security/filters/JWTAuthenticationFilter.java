package com.photomart.authorizationservice.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.photomart.authorizationservice.models.request.AuthenticationRequest;
import com.photomart.authorizationservice.models.response.AuthenticationResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            AuthenticationRequest authenticationRequest = objectMapper.readValue(request.getInputStream(),
                    AuthenticationRequest.class);
            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserEmail(),
                            authenticationRequest.getPassword())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(authenticationResponse));

    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(failed.getMessage()));
    }
}
