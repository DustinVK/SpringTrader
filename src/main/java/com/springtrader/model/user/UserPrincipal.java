package com.springtrader.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = -25648261130124405L;
	private User user;
	
	public UserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		this.user.getPermissionList().forEach(p -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(p);
			authorities.add(authority);
		});
		
		this.user.getRoleList().forEach(p -> {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + p);
			authorities.add(authority);
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}
