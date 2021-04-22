package com.springtrader.SpringTrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springtrader.SpringTrader.model.internal.balance.Balance;
import com.springtrader.SpringTrader.model.internal.balance.BalanceDAO;

@Component
public class BalanceService {
	@Autowired
	BalanceDAO balanceDAO;
	
	public List<Balance> getBalanceList(String username){
		return balanceDAO.getBalancesByUsername(username);
	}
}
