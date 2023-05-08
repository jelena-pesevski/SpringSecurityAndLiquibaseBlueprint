package com.example.demo.model.repository;

import com.example.demo.model.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByUsername(String username);
}
