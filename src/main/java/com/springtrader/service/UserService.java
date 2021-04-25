package com.springtrader.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springtrader.model.user.User;
import com.springtrader.model.user.UserDAO;
import com.springtrader.model.user.stock.UserStock;
import com.springtrader.model.user.stock.UserStockDAO;

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
	
	public List<UserStock> getUserPortfolioStocks(String username, long portfolioId) {
		userStockDAO = new UserStockDAO();
		userStockDAO.setDataSource(dataSource);
		return userStockDAO.getUserPortfolioStocks(username, portfolioId);
	}
	
}
