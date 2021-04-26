package com.springtrader.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtrader.model.portfolio.Portfolio;
import com.springtrader.model.portfolio.PortfolioDAO;

@Service 
public class PortfolioService {

	@Autowired
	DataSource dataSource;
	
	public List<Portfolio> getPortfolios(String username) {
		PortfolioDAO pDao = new PortfolioDAO();
		pDao.setDataSource(dataSource);
		return pDao.getUserPortfolios(username);
	}
	
	public String addPortfolio(String username, String portfolioName) {
		PortfolioDAO pDao = new PortfolioDAO();
		pDao.setDataSource(dataSource);
		return pDao.addPortfolio(username, portfolioName);
	}

}
