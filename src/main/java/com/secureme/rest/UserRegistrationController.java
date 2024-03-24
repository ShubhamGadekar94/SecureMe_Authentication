package com.secureme.rest;

import com.secureme.dto.UserRegistration;
import com.secureme.entity.UserInfo;
import com.secureme.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService UserRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistration userDetails){
        UserInfo userInfo = UserRegistrationService.registerUser(userDetails);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
