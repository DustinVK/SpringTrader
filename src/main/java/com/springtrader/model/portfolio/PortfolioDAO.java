package com.springtrader.model.portfolio;


import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springtrader.service.ExternalStockService;
import com.springtrader.util.PortfolioUtil;

@Component
public class PortfolioDAO {
	
	private PortfolioUtil portfolioUtil = new PortfolioUtil();

	private JdbcTemplate jdbc;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbc= new JdbcTemplate(dataSource);
     }
    
    public List<Portfolio> getUserPortfolios(String username, ExternalStockService stockService){
    	List<Portfolio> list = new LinkedList<Portfolio>();
    	portfolioUtil.setStockService(stockService);
    	String sql = "SELECT * FROM userPortfolios WHERE username = ?" +
				"ORDER BY name DESC ";		
		List<PortfolioMetaData> portfolioList = jdbc.query(sql, new PortfolioMetaRowMapper(), username);
		
	
		for(PortfolioMetaData meta : portfolioList) {
			Portfolio portfolio = extractPortfolio(username, meta);
			list.add(portfolio);
		}
		
		return list;
    }

	private Portfolio extractPortfolio(String username, PortfolioMetaData meta) {
		Portfolio portfolio = new Portfolio();
		Long id = meta.getId();
		List<TradeRow> rows = getTransactions(username, id); 
		portfolio = portfolioUtil.getPortfolio(rows);
		portfolio.getMetaData().setName(meta.getName());
		portfolio.getMetaData().setId(id);
		portfolio.getMetaData().setUsername(meta.getUsername());
		return portfolio;
	}

	public List<TradeRow> getTransactions(String username, Long id) {
		String sql = "SELECT * FROM portfolios WHERE username = ? AND id = ? " +
				"ORDER BY stamp DESC ";
		List<TradeRow> rows = jdbc.query(sql, new TradeRowMapper(), username, id);
		return rows;
	}
    
    public String getPortfolioUser(long id) {
    	String sql = "SELECT * FROM userPortfolios WHERE id = ?";
    	HashMap<String, Long> params = new HashMap<String, Long>();
    	params.put("id", id);
		List<PortfolioMetaData> portfolioList = jdbc.query(sql, new PortfolioMetaRowMapper(), id);
		return portfolioList.get(0).getUsername();
    	
    }
    
    public String addPortfolio(String username, String portfolioName) {
    	String sql = "INSERT INTO userPortfolios(username, name) VALUES (?, ?)";
    	int result = jdbc.update(sql, username, portfolioName);
    	if(result != 1) {
    		return "Something went wrong trying to add portfolio :(";
    	}
    	return "Portfolio Added!";
    }
    
    public String deletePortfolio(long id) {
    	deletePortfolioRows(id);
    	return deleteUserPortfolio(id);
    }

	private String deleteUserPortfolio(long id) {
		String sql = "DELETE FROM userPortfolios WHERE id = ?";
    	int result = jdbc.update(sql, id);
    	if(result != 1) {
    		return "Something went wrong trying to delete portfolio...";
    	}
    	return "Portfolio Deleted!";
	}
    
    private String deletePortfolioRows(long id) {
    	String sql = "DELETE FROM portfolios WHERE id = ?";
    	int r= jdbc.update(sql, id);
    	return String.valueOf(r);
    }
    
    
    public String addTransaction(long id, String username, TradeRow transaction) {
    	String sql = "INSERT INTO portfolios(username, id, symbol, amount, price, stamp) VALUES (?, ?, ?, ?, ?, ?)";
    	int result = jdbc.update(sql, username, id, transaction.getSymbol(), transaction.getAmount(), transaction.getPrice(), transaction.getStamp());
    	return String.valueOf(result);
    }
	
}
