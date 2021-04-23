package com.springtrader.SpringTrader.model.internal.userStock;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserStockDAO {

	private JdbcTemplate jdbc;
	
    public void setDataSource(DataSource dataSource) {
        this.jdbc= new JdbcTemplate(dataSource);
     }
    
    public List<UserStock> getUserStocks(String username){
    	final String sql = "SELECT * FROM userStocks WHERE username = ? " +
				"ORDER BY stamp DESC ";		
		return jdbc.query(sql, new UserStockRowMapper(), username);
    }
    
}
