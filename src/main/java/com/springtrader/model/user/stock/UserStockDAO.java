package com.springtrader.model.user.stock;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserStockDAO {

	private JdbcTemplate jdbc;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbc= new JdbcTemplate(dataSource);
     }
    
    public List<UserStock> getUserPortfolioStocks(String username, long portfolioId){
    	final String sql = "SELECT * FROM portfolios WHERE username = ? AND id = ?" +
				"ORDER BY stamp DESC ";		
		return jdbc.query(sql, new UserStockRowMapper(), username, portfolioId);
    }
//    

}
