package com.springtrader.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.springtrader.model.portfolio.Portfolio;
import com.springtrader.model.portfolio.PortfolioDAO;
import com.springtrader.model.portfolio.PortfolioRow;
import com.springtrader.model.user.stock.UserStock;

@Service 
public class PortfolioService {

	@Autowired
	DataSource dataSource;
	
	@Autowired 
	UserStockService uss;
	
	public List<Portfolio> getPortfolios(String username) {
		PortfolioDAO pDao = new PortfolioDAO();
		pDao.setDataSource(dataSource);
		System.out.println(pDao.getUserPortfolios(username).toString());
		return pDao.getUserPortfolios(username);
		
	}

}
