package com.photoMart.userservice.repository;

import com.photoMart.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUserIdEquals(@NonNull long userId);
    User findByUserMailIgnoreCase(@NonNull String userMail);
    boolean existsByUserMailIgnoreCase(@NonNull String userMail);

}
