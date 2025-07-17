package com.secureme.rest;

import com.secureme.dto.UserRoles;
import com.secureme.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesSeeder {

    @Autowired
    private RolesService rolesService;

    @PostMapping("")
    public ResponseEntity<String> addRoles(@RequestBody List<UserRoles> roles) {
        rolesService.addRoles(roles);
        return new ResponseEntity<>("Roles added successfully", HttpStatus.OK);
    }
}
