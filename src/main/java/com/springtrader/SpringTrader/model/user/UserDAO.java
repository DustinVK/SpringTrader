package com.springtrader.SpringTrader.model.user;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	@Autowired
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
	
	public boolean userExists(String username) {
		String sql = "Select * from users WHERE username = ?";  
		List<User> list = jdbc.query(sql, new UserRowMapper(), username);
		return (!list.isEmpty());
	}
}
