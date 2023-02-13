package com.photoMart.userservice.security.config;

import com.photoMart.userservice.security.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    AuthorizationFilter authorizationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        try {
            http
                    .cors().and()
                    .csrf().disable()
                    .authorizeHttpRequests(authorize-> authorize
                            .requestMatchers(HttpMethod.GET ,"/api/v1/users/**").hasAnyAuthority("USER","SERVICE")
                            .requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasAnyAuthority("SERVICE")
//                            .requestMatchers(HttpMethod.PUT,"/api/v1/users/**").hasAuthority("")
//                            .requestMatchers(HttpMethod.DELETE ,"api/v1/calendars/**").hasAuthority("PHOTOGRAPHER")
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(authorizationFilter,UsernamePasswordAuthenticationFilter.class)
//                    .addFilter(authorizationFilter)
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
