package com.secureme.service;

import com.secureme.dto.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.secureme.dto.AuthenticationResponse;
import com.secureme.dto.JwtAuthenticationModel;

import java.util.Objects;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;

	@Autowired
	private JwtService jwtService;

	public String authenticateUser(JwtAuthenticationModel authenticationModel) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationModel.getUsername(), authenticationModel.getPassword()));
		}catch (Exception e){
			e.printStackTrace();
		}

		if (authentication.isAuthenticated()) {
			UserInfoUserDetailsService userDetails = 
					(UserInfoUserDetailsService) authentication.getPrincipal();
	
				String token= jwtService.generateToken(userDetails);
				return token;
		}else {
			return null;
		}

	}

	public UserRegistration isTokenValid(String token) {

		String username = jwtService.extractUsername(token);
		if(Objects.nonNull(username)){
			UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(username);
			if(jwtService.validateToken(token, userDetails)){
				return UserRegistration.builder()
						.username(userDetails.getUsername())
						.build();
			}else{
				throw new RuntimeException("invalid token");
			}
		}else{
			throw new RuntimeException("invalid token");
		}
	}

}
