package com.springtrader.model.user;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    private String username;
    
    private String password;
    
    private boolean enabled;
    
    private String roles = "";
    
    private String permissions= "";
    
    public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}
	
	public List<String> getRoleList(){
		if(this.roles.length() > 0 && this.roles != null) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	
	public List<String> getPermissionList(){
		if(this.permissions.length() > 0 && this.permissions != null) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public User(String username, String password, String roles, String permissions) {
    	this.username = username;
    	this.password = password;
    	this.roles = roles;
    	this.permissions = permissions;
    }
	
  
    public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() { }


    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}