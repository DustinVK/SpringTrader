package com.springtrader.SpringTrader.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springtrader.SpringTrader.model.internal.balance.Balance;
import com.springtrader.SpringTrader.model.internal.balance.BalanceDAO;
import com.springtrader.SpringTrader.model.internal.user.UserDAO;

@Component
public class BalanceService {
	@Autowired
	BalanceDAO balanceDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	
	public List<Balance> getBalanceList(String username){
		if(userNameValid(username)) {
			return balanceDAO.getBalancesByUsername(username);
		}
		return new ArrayList<Balance>();
	}
	
	public Balance getLatestBalance(String username) {
		if(userNameValid(username)) {
			return balanceDAO.getLatestBalance(username);
		}
		return new Balance();
	}



	private boolean userNameValid(String username) {
		return userDAO.userExists(username);
	}
}
