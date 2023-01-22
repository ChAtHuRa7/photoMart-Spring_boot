package com.photomart.authorizationservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authorities_table")
@Getter
@Setter
@NoArgsConstructor
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorities;

    public Authorities(String authorities) {
        this.authorities = authorities;
    }
}
