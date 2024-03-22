package com.secureme.service;

import com.secureme.dto.UserRegistration;
import com.secureme.entity.Roles;
import com.secureme.entity.UserInfo;
import com.secureme.repo.RolesRepo;
import com.secureme.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserInfo registerUser(UserRegistration userDetails){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userDetails.getUsername());
        userInfo.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userInfo.setEmail(userDetails.getEmail());
        userInfo.setAccountNonExpired(true);
        userInfo.setAccountNonLocked(true);
        userInfo.setCredentialsNonExpired(true);
        userInfo.setEnable(true);
        Set<Roles> roleDetails = userDetails.getRoles().stream().map(role-> {
            Roles roles = null;
            try {
                roles = rolesRepo.findByRoleName(role.getRoleName()).orElseThrow(() -> new Exception("invalid role"));
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
