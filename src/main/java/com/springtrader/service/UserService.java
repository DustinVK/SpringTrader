package com.springtrader.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtrader.model.user.User;
import com.springtrader.model.user.UserDAO;

@Service
public class UserService {

	
	@Autowired
	UserDAO userDAO;
	
	@Autowired 
	DataSource dataSource;
	
	
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
	

}
