package com.photomart.portfolioservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "portfolio_source_url")
@Getter
@Setter
@NoArgsConstructor
public class SourceUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    public SourceUrl(String url) {
        this.url = url;
    }
}
