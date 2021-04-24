package com.springtrader.SpringTrader.model.balance;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BalanceDAO {
	private JdbcTemplate jdbc;
	   
    public void setDataSource(DataSource dataSource) {
       this.jdbc= new JdbcTemplate(dataSource);
    }
	public List<Balance> getBalancesByUsername(String username) {
		final String sql = "SELECT * FROM balances WHERE username = ? " +
				"ORDER BY stamp DESC ";		
		return jdbc.query(sql, new BalanceRowMapper(), username);
	}
	public Balance getLatestBalance(String username) {
		final String sql = "SELECT * FROM balances WHERE username = ? " +
				"ORDER BY stamp DESC " +
				"LIMIT 1";		
		List<Balance> list = jdbc.query(sql, new BalanceRowMapper(), username);
		return list.get(0);
	}
}
