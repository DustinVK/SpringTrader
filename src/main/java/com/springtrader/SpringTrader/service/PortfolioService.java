package com.springtrader.SpringTrader.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springtrader.SpringTrader.model.internal.user.User;
import com.springtrader.SpringTrader.model.internal.user.UserDAO;
import com.springtrader.SpringTrader.model.internal.userStock.UserStock;
import com.springtrader.SpringTrader.model.internal.userStock.UserStockDAO;

@Component
public class PortfolioService {
	private UserStockDAO userStockDAO;
	private UserDAO userBalanceDAO;
	
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