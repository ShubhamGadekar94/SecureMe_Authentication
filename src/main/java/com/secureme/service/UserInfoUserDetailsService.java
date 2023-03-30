package com.secureme.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.secureme.entity.Roles;
import com.secureme.entity.UserInfo;

public class UserInfoUserDetailsService implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Set<Roles> roles;
	private String userName;
	private String password;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnable;

	public UserInfoUserDetailsService(UserInfo userInfo) {
		this.roles = userInfo.getRoles();
		this.userName = userInfo.getUserName();
		this.password = userInfo.getPassword();
		this.isAccountNonExpired = userInfo.isAccountNonExpired();
		this.isAccountNonLocked = userInfo.isAccountNonLocked();
		this.isCredentialsNonExpired = userInfo.isCredentialsNonExpired();
		this.isEnable = userInfo.isEnable();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(Roles::getRoleName)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnable;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
