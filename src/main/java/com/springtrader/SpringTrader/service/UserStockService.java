package com.springtrader.SpringTrader.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springtrader.SpringTrader.model.user.User;
import com.springtrader.SpringTrader.model.user.UserDAO;
import com.springtrader.SpringTrader.model.userStock.UserStock;
import com.springtrader.SpringTrader.model.userStock.UserStockDAO;
import com.springtrader.SpringTrader.model.userStock.UserStockTimeSort;

@Service
public class UserStockService {
	private UserStockDAO userStockDAO;
	
	@Autowired 
	DataSource dataSource;
	
	public List<UserStock> getUserStocks(String username) {
		userStockDAO = new UserStockDAO();
		userStockDAO.setDataSource(dataSource);
		return userStockDAO.getUserStocks(username);
	}
	
	public List<UserStock> getLatestUserStocks(String username) {

		List<UserStock> list = getUserStocks(username);
		if(list.isEmpty()) {
			System.out.println("Empty userstock list....");
			return list;
		}
		else {
			for(UserStock stock : list) {
				if(stock == null) {
					System.out.println("null stock...");
				} else {
					System.out.println(stock.toString());
				}
			}
		}
		List<UserStock> returnList = new ArrayList<UserStock>();
 		Collections.sort(list, new UserStockTimeSort());
		
		getUniqueUserStocksBySymbol(list, returnList);
		
		return returnList;
	}

	private void getUniqueUserStocksBySymbol(List<UserStock> list, List<UserStock> returnList) {
		HashSet<String> hSet = new HashSet<String>();
		for(UserStock stock : list) {
			if(!hSet.contains(stock.getSymbol())) {
				returnList.add(stock);
				hSet.add(stock.getSymbol());
			}
		}
	}
	
	
}