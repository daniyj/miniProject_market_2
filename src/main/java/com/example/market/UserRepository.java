package com.example.market;

import com.example.market.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUserId(String userId);
    Optional<UserEntity> findByUserId(String userId);
}
