package com.springtrader.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtrader.model.portfolio.Portfolio;
import com.springtrader.model.portfolio.PortfolioDAO;
import com.springtrader.model.portfolio.TradeRow;

@Service 
public class PortfolioService {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	ExternalStockService stockService;
	
	public List<Portfolio> getPortfolios(String username) {
		PortfolioDAO pDao = new PortfolioDAO();
		pDao.setDataSource(dataSource);
		return pDao.getUserPortfolios(username, stockService);
	}
	
	public String addPortfolio(String username, String portfolioName) {
		PortfolioDAO pDao = new PortfolioDAO();
		pDao.setDataSource(dataSource);
		return pDao.addPortfolio(username, portfolioName);
	}
	
	public String deletePortfolio(long id, String username) {
		PortfolioDAO pDao = new PortfolioDAO();
		pDao.setDataSource(dataSource);
		String portfolioUser = pDao.getPortfolioUser(id);
		if(portfolioUser.equals(username)) {
			return pDao.deletePortfolio(id);
		}
		return "You can't do that...";
	}
	
	public String addTransaction(long id, String username, TradeRow transaction) {
		PortfolioDAO pDao = new PortfolioDAO();
		pDao.setDataSource(dataSource);
		String portfolioUser = pDao.getPortfolioUser(id);
		if(portfolioUser.equals(username)) {
			return pDao.addTransaction(id, username, transaction);
		}
		return "You can't do that...";
	}
	
	public List<TradeRow> getTransactions(String username, long id){
		PortfolioDAO pDao = new PortfolioDAO();
		pDao.setDataSource(dataSource);
		return pDao.getTransactions(username, id);
	}
	
}
