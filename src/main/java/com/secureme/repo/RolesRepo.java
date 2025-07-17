package com.secureme.repo;

import com.secureme.entity.Roles;
import com.secureme.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {

	Optional<Roles> findByRoleName(String roleName);

}
