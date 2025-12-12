package com.insurance.auth_service.service;

import com.insurance.auth_service.dto.SignupRequest;

public interface AuthService {

	String signup(SignupRequest request);

}