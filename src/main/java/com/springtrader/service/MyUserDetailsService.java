package com.springtrader.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springtrader.model.user.User;
import com.springtrader.model.user.UserDAO;
import com.springtrader.model.user.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	DataSource dataSource;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDAO ud = new UserDAO();
		ud.setDataSource(dataSource);
		User user = ud.getUserByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
