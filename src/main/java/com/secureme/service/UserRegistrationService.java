package com.secureme.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.secureme.dto.UserRegistration;
import com.secureme.entity.Roles;
import com.secureme.entity.UserInfo;
import com.secureme.exception.ResourceAlreadyExistException;
import com.secureme.repo.RolesRepo;
import com.secureme.repo.UserInfoRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private RolesRepo rolesRepo;

    @Transactional
    public UserInfo registerUser(UserRegistration userDetails){

        if(userInfoRepo.findByEmailIgnoreCase(userDetails.getEmail()).isPresent())
            throw new ResourceAlreadyExistException("user already exist");

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userDetails.getUsername());
        userInfo.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userInfo.setEmail(userDetails.getEmail().toUpperCase());
        userInfo.setAccountNonExpired(true);
        userInfo.setAccountNonLocked(true);
        userInfo.setCredentialsNonExpired(true);
        userInfo.setEnable(true);
        Set<Roles> roleDetails = userDetails.getRoles().stream().map(role-> {
            Roles roles = null;
            try {
                roles = rolesRepo.findByRoleName(role.getRoleName()).orElseThrow(() -> new Exception(String.format("invalid role %s", role.getRoleName())));
                roles.setUserInfo(Set.of(userInfo));
                return roles;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());
        userInfo.setRoles(roleDetails);
        return userInfoRepo.save(userInfo);
    }

}
