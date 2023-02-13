package com.photomart.authorizationservice.security.config;
import com.photomart.authorizationservice.models.request.AuthenticationRequest;
import com.photomart.authorizationservice.security.filters.AuthorizationFilter;
import com.photomart.authorizationservice.security.filters.JwtValidateFilter;
import com.photomart.authorizationservice.security.services.ApplicationUserDetailsService;
import jakarta.servlet.Filter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
class WebSecurityConfig{

    @Autowired
    private JwtValidateFilter jwtValidateFilter;

    @Autowired
    ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserDetailsService);
    }

//    @Bean
//    public PasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        try {
            http
                    .cors().and()
                    .csrf().disable()
                    .authorizeHttpRequests(authorize-> authorize
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers(HttpMethod.POST ,"/api/v1/auth/users").permitAll()
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtValidateFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
