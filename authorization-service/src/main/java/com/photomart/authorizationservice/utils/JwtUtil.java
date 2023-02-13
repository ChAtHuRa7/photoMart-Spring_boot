package com.photomart.authorizationservice.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    public String extractUsername(String token) throws Exception {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) throws Exception{
        try {
            return extractClaim(token, Claims::getExpiration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws Exception{
        final Claims claims;
        try {
            claims = extractAllClaims(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) throws Exception{
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

    }

    private Boolean isTokenExpired(String token) throws Exception {
        return extractExpiration(token).before(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
    }

    public String createToken(UserDetails userDetails) {
        return Jwts.builder().
                claim("authorities",userDetails.getAuthorities())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(60).toInstant(ZoneOffset.of("+05:30"))))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username;
        try {
            username = extractUsername(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
