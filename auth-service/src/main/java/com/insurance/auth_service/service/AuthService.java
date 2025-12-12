package com.insurance.auth_service.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.insurance.auth_service.dto.SignupRequest;

public interface AuthService {

	String signup(SignupRequest request);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}