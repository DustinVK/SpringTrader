package com.springtrader.SpringTrader.model.internal.user;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbc;
	   
    public void setDataSource(DataSource dataSource) {
       this.dataSource = dataSource;
       this.jdbc= new JdbcTemplate(dataSource);
    }
	      
	public User getUserByUsername(String username) {
		final String sql = "SELECT * FROM users WHERE username = ?";
		User user = jdbc.queryForObject(sql, new UserRowMapper(), username);
		return user;
	}
}
