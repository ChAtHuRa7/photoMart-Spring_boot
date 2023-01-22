package com.photomart.portfolioservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "portfolio_service")
@Getter
@Setter
@NoArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long photographerId;
    private String location;
    private String albumName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SourceUrl> sourceUrls;


    public Portfolio(long photographerId, String location, String albumName) {
        this.photographerId = photographerId;
        this.location = location;
        this.albumName = albumName;
    }
}
