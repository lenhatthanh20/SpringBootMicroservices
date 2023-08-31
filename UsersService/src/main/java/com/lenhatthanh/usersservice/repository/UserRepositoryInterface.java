package com.lenhatthanh.usersservice.repository;

import com.lenhatthanh.usersservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryInterface extends JpaRepository<UserEntity, String> {
}