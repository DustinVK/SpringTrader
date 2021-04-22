package com.springtrader.SpringTrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springtrader.SpringTrader.model.internal.user.User;
import com.springtrader.SpringTrader.model.internal.user.UserDAO;

@Component
public class UserService {

	@Autowired
	UserDAO dao;
	
	public User getUserByUsername(String username) {
		return dao.getUserByUsername(username);}
	
}
