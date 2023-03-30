package com.secureme.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secureme.dto.AuthenticationResponse;
import com.secureme.dto.JwtAuthenticationModel;
import com.secureme.service.AuthenticationService;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("")
	public ResponseEntity<AuthenticationResponse> authenticateUser(
			@RequestBody JwtAuthenticationModel authenticationModel) {
		String jwtToken = authenticationService.authenticateUser(authenticationModel);
		return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
	}
	
	@PostMapping("/authorize")
	public ResponseEntity<Boolean> authorizeToken(@RequestBody String token) {
		Boolean validToken= authenticationService.isTokenValid(token);
		return new ResponseEntity<>(validToken, HttpStatus.OK);
	}

}
