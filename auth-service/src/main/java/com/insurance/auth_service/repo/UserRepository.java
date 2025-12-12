package com.insurance.auth_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.auth_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}