package com.secureme.rest;

import com.secureme.dto.UserRegistration;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.secureme.dto.AuthenticationResponse;
import com.secureme.dto.JwtAuthenticationModel;
import com.secureme.service.AuthenticationService;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/authentication")
	public ResponseEntity<AuthenticationResponse> authenticateUser(
			@RequestBody JwtAuthenticationModel authenticationModel) {
		String jwtToken = authenticationService.authenticateUser(authenticationModel);
		return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
	}
	
	@GetMapping("/validateToken")
	public ResponseEntity<UserRegistration> authorizeToken(@RequestParam("token") String token) {
		UserRegistration response= authenticationService.isTokenValid(token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
