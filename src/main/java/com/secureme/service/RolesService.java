package com.secureme.service;

import com.secureme.dto.UserRoles;
import com.secureme.entity.Roles;
import com.secureme.repo.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesRepo rolesRepo;

    public void addRoles(List<UserRoles> roles) {
        roles.forEach(role -> {
            rolesRepo.findByRoleName(role.getRoleName())
                    .orElseGet(() -> {
                        Roles roleEntity = new Roles();
                        roleEntity.setRoleName(role.getRoleName());
                        return rolesRepo.save(roleEntity);
                    });
        });
    }
}
