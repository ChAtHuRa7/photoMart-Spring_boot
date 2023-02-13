package com.photomart.authorizationservice.security.filters;

import com.photomart.authorizationservice.security.services.ApplicationUserDetailsService;
import com.photomart.authorizationservice.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.SecurityContext;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtValidateFilter extends OncePerRequestFilter {


    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        System.out.println("@@ ## 1 ## @@");

        final String authorizationHeader = request.getHeader("Authorization");

        System.out.println("@@ ## 2 ## @@");

        String userName = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){

            System.out.println("@@ ## 3 ## @@");

            jwt = authorizationHeader.substring(7);

            System.out.println("@@ ## 4 ## @@");

            System.out.println("@@ ## " + jwt + " ## @@");

            try {
                userName = jwtUtil.extractUsername(jwt);

                System.out.println("@@ ## 5 ## @@");

            } catch (Exception e) {


                System.out.println("@@ ## " + e + " ## @@");
                System.out.println("@@ ## " + e.getMessage() + " ## @@");


                System.out.println("@@ ## 6 ## @@");

                response.setStatus(HttpStatus.SC_NOT_ACCEPTABLE);
                filterChain.doFilter(request,response);
                return;
//                response.sendError(HttpStatus.SC_NOT_ACCEPTABLE,"Token not valid");
            }
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

            System.out.println("@@ ## 7 ## @@");

            UserDetails userDetails = this.applicationUserDetailsService.loadUserByUsername(userName);

            System.out.println("@@ ## 8 ## @@");

            if(jwtUtil.validateToken(jwt,userDetails)){

                System.out.println("@@ ## 9 ## @@");

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());

                System.out.println("@@ ## 10 ## @@");

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                System.out.println("@@ ## 11 ## @@");

                request.setAttribute("userName",userName);
                request.setAttribute("authorities",userDetails.getAuthorities());
                request.setAttribute("jwt",jwt);
            }
            else {

                System.out.println("@@ ## 12 ## @@");

               response.setStatus(HttpStatus.SC_NOT_ACCEPTABLE);
               filterChain.doFilter(request,response);
               return;
//                response.sendError(HttpStatus.SC_NOT_ACCEPTABLE,"Token not valid");
            }
        }

        filterChain.doFilter(request,response);
    }
}
