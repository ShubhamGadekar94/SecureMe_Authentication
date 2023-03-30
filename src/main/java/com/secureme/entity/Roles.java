package com.secureme.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Roles implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false, unique = true, updatable = false)
	private Integer roleId;
	
	private String roleName;
	
	@JsonIgnoreProperties(value = "roles")
	@ManyToMany(mappedBy = "roles")
	Set<UserInfo> userInfo= new HashSet<>();
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<UserInfo> getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(Set<UserInfo> userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
