package com.secureme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.secureme.entity.UserInfo;
import com.secureme.repo.UserInfoRepo;



@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepo userInfoRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userInfo = userInfoRepo.findByUserName(username);
		return userInfo.map(UserInfoUserDetailsService::new)
				.orElseThrow(() -> new UsernameNotFoundException("invalid username/password"));
	}

}
