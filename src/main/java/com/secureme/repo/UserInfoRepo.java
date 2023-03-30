package com.secureme.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secureme.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByUserName(String username);

}
