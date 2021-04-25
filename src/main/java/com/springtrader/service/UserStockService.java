package com.springtrader.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springtrader.model.portfolio.PortfolioRow;
import com.springtrader.model.user.User;
import com.springtrader.model.user.UserDAO;
import com.springtrader.model.user.stock.UserStock;
import com.springtrader.model.user.stock.UserStockDAO;
import com.springtrader.model.user.stock.UserStockTimeSort;

@Service
public class UserStockService {
	private UserStockDAO userStockDAO;
	
	@Autowired 
	DataSource dataSource;
	
	@Autowired
	ExternalStockService stockService;
	
	public List<UserStock> getUserPortfolioStocks(String username, long portfolioId) {
		userStockDAO = new UserStockDAO();
		userStockDAO.setDataSource(dataSource);
		return userStockDAO.getUserPortfolioStocks(username, portfolioId);
	}
	
	public List<UserStock> getLatestUserPortfolioStocks(String username, long portfolioId) {

		List<UserStock> list = getUserPortfolioStocks(username, portfolioId);
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
//	
//	public List<PortfolioRow>getPortfolio(String username, long portfolioId){
//		List<UserStock> userStockList = getLatestUserPortfolioStocks(username, portfolioId);
//		List<PortfolioRow> portfolio = getPortfolioFromUserStocks(userStockList);	
//		return portfolio;
//	}
//
//	private List<PortfolioRow> getPortfolioFromUserStocks(List<UserStock> userStockList) {
//		List<PortfolioRow> portfolio = new ArrayList<PortfolioRow>();
//		PortfolioRow row;
//		for(UserStock userStock : userStockList) {
//			String symbol = userStock.getSymbol();
//			String priceStr = stockService.getStockQuote(symbol).getPrice();
//			BigDecimal price = new BigDecimal(priceStr);
//			row = new PortfolioRow();
//			row.setPrice(price);
//			row.setSymbol(symbol);
//			row.setAmount(userStock.getAmount());
//			row.setHoldings(calculateHoldings(price, row));	
//			portfolio.add(row);
//		}
//		return portfolio;
//	}
//
//
//	
	
}