package com.secureme.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secureme.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByUserName(String username);
	Optional<UserInfo> findByEmailIgnoreCase(String email);

}
