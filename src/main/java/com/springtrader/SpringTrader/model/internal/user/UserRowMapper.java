package com.springtrader.SpringTrader.model.internal.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setPermissions(rs.getString("permissions"));
		user.setRoles(rs.getString("roles"));
		user.setEnabled(rs.getBoolean("enabled"));
		return user;
	}

}
