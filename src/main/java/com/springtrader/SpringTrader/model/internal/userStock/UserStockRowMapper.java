package com.springtrader.SpringTrader.model.internal.userStock;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserStockRowMapper implements RowMapper<UserStock>{

	@Override
	public UserStock mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserStock userStock = new UserStock();
		userStock.setUsername(rs.getString("username"));
		userStock.setSymbol(rs.getNString("symbol"));
		userStock.setAmount(rs.getBigDecimal("amount"));
		userStock.setPrice(rs.getBigDecimal("price"));
		userStock.setStamp(rs.getTimestamp("stamp"));
		return userStock;
	}

}
