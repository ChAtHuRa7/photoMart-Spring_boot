package com.photomart.authorizationservice.repository;
import com.photomart.authorizationservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {
    Users findByUserEmailIgnoreCase(@NonNull String userEmail);
    boolean existsByUserEmailEqualsIgnoreCase(@NonNull String userEmail);
}
