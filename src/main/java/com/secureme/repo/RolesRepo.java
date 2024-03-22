package com.secureme.repo;

import com.secureme.entity.Roles;
import com.secureme.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepo extends JpaRepository<Roles, Integer> {

	Optional<Roles> findByRoleName(String roleName);

}
