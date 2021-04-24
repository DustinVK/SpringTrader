package com.springtrader.SpringTrader.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springtrader.SpringTrader.model.user.User;
import com.springtrader.SpringTrader.model.user.UserDAO;
import com.springtrader.SpringTrader.model.userStock.UserStock;
import com.springtrader.SpringTrader.model.userStock.UserStockDAO;

@Service
public class UserService {
	private UserStockDAO userStockDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired 
	DataSource dataSource;
	
	
	public User getUserByUsername(String username) {
		userStockDAO = new UserStockDAO();
		userStockDAO.setDataSource(dataSource);
		return userDAO.getUserByUsername(username);
	}
	
	public List<UserStock> getUserStocks(String username) {
		userStockDAO = new UserStockDAO();
		userStockDAO.setDataSource(dataSource);
		return userStockDAO.getUserStocks(username);
	}
	
}
