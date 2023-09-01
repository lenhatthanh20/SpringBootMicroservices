package com.lenhatthanh.usersservice.repository;

import com.lenhatthanh.usersservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryInterface extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
