package com.springtrader.SpringTrader.model.internal.balance;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BalanceDAO {
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbc;
	   
    public void setDataSource(DataSource dataSource) {
       this.dataSource = dataSource;
       this.jdbc= new JdbcTemplate(dataSource);
    }
	public List<Balance> getBalancesByUsername(String username) {
		final String sql = "SELECT * FROM balances WHERE username = ?";		
		return jdbc.query(sql, new BalanceRowMapper(), username);
	}
}
