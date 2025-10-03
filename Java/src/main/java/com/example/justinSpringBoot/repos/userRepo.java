package com.example.justinSpringBoot.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.justinSpringBoot.entity.*;

public interface userRepo extends JpaRepository<userEntity, Long>{
	Optional<userEntity> findByUsername(String username);
}
