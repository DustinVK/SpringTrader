package com.springtrader.model.portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springtrader.model.user.stock.UserStock;
import com.springtrader.service.UserStockService;

public class PortfolioDAO {
	
	@Autowired
	UserStockService uss;

	private JdbcTemplate jdbc;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbc= new JdbcTemplate(dataSource);
     }
    
    public List<Portfolio> getUserPortfolios(String username){
    	List<Portfolio> list = new LinkedList<Portfolio>();
    	String sql = "SELECT * FROM userPortfolios WHERE username = ?" +
				"ORDER BY name DESC ";		
		List<PortfolioMetaData> portfolioList = jdbc.query(sql, new PortfolioMetaRowMapper(), username);
		
		sql = "SELECT * FROM portfolios WHERE username = ? AND id = ? " +
				"ORDER BY stamp DESC ";
		for(PortfolioMetaData meta : portfolioList) {
			Portfolio portfolio = new Portfolio();
			Long id = meta.getId();
			List<PortfolioRow> rows = jdbc.query(sql, new PortfolioRowMapper(), username, id); // TODO: probably dont need username here
			portfolio.setMetaData(meta);
			portfolio.setList(getUniqueSymbolPortfolioRows(rows));
			list.add(portfolio);
		}
		
		return list;
    }
    
	private List<PortfolioRow> getUniqueSymbolPortfolioRows(List<PortfolioRow> list) {
		List<PortfolioRow> returnList = new ArrayList<>();
		HashSet<String> hSet = new HashSet<String>();
		for(PortfolioRow row : list) {
			if(!hSet.contains(row.getSymbol())) {
				returnList.add(row);
				hSet.add(row.getSymbol());
			}
		}
		return returnList;
	}
    
//    public List<PortfolioRow>getPortfolio(String username, long portfolioId){
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

	
	
//}
	
}
