package com.secureme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.secureme.dto.AuthenticationResponse;
import com.secureme.dto.JwtAuthenticationModel;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	public String authenticateUser(JwtAuthenticationModel authenticationModel) {
		Authentication auththentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationModel.getUsername(), authenticationModel.getPassword()));

		if (auththentication.isAuthenticated()) {
			UserInfoUserDetailsService userDetails = 
					(UserInfoUserDetailsService) auththentication.getPrincipal();
	
				String token= jwtService.generateToken(userDetails);
				return token;
		}else {
			return null;
		}

	}

	public Boolean isTokenValid(String token) {
		return jwtService.validateToken(token);
	}

}
