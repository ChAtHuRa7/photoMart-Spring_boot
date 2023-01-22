package com.photomart.portfolioservice.repository;

import com.photomart.portfolioservice.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio,Long> {
    long countByPhotographerId(@NonNull long photographerId);
    List<Portfolio> findByPhotographerId(@NonNull long photographerId);

}
