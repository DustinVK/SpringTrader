package com.springtrader.SpringTrader.util;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springtrader.SpringTrader.model.internal.user.UserPrincipal;

@Component
public class UserSecurity {
	public boolean hasUserAuthority(Authentication authentication, String userId) {
		return authentication.getName().equals(userId);
	}
}
