package com.insurance.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insurance.auth_service.dto.SignupRequest;
import com.insurance.auth_service.entity.User;
import com.insurance.auth_service.repo.UserRepository;

@Service
public class AuthServiceImplementation implements AuthService,  UserDetailsService {

	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  PasswordEncoder passwordEncoder;

	public String signup(SignupRequest request) {
    	System.out.println("auth service");


		// Check if user already exists
		if (userRepository.existsByEmail(request.getEmail())) {
			return "Email already exists!";
		}

		if (userRepository.existsByUsername(request.getUsername())) {
			return "Username already exists!";
		}

		// Create new user
		User user = new User();
		user.setFullName(request.getFullName());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setAddress(request.getAddress());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setGender(request.getGender());
		user.setRole("USER"); // only one role for now
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		userRepository.save(user);

		return "Signup successful!";
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities("USER")
            .build();
    }

}