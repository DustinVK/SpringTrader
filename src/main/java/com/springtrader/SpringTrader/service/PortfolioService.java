package com.springtrader.SpringTrader.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.springtrader.SpringTrader.model.portfolio.PortfolioRow;
import com.springtrader.SpringTrader.model.userStock.UserStock;

@Service 
public class PortfolioService {
	@Autowired
	UserStockService userStockService;
	
	@Autowired
	StockService stockService;
	
	public List<PortfolioRow>getPortfolio(String username){
		List<UserStock> userStockList = userStockService.getLatestUserStocks(username);
		List<PortfolioRow> portfolio = getPortfolio(userStockList);	
		return portfolio;
	}

	private List<PortfolioRow> getPortfolio(List<UserStock> userStockList) {
		List<PortfolioRow> portfolio = new ArrayList<PortfolioRow>();
		PortfolioRow row;
		for(UserStock userStock : userStockList) {
			String symbol = userStock.getSymbol();
			String priceStr = stockService.getStockQuote(symbol).getPrice();
			BigDecimal price = new BigDecimal(priceStr);
			row = new PortfolioRow();
			row.setPrice(price);
			row.setSymbol(symbol);
			row.setAmount(userStock.getAmount());
			row.setHoldings(calculateHoldings(price, row));	
			portfolio.add(row);
			System.out.printf("\nsymbol: %s Holdings: %s \n", symbol, calculateHoldings(price, row).toString());
		}
		return portfolio;
	}

	private BigDecimal calculateHoldings(BigDecimal price, PortfolioRow row) {
		return price.multiply(row.getAmount());
	}
}
