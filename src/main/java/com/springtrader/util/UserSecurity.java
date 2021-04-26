package com.springtrader.util;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {
	public boolean hasUserAuthority(Authentication authentication, String userId) {
		return authentication.getName().equals(userId);
	}
}
