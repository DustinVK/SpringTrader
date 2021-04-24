package com.springtrader.SpringTrader.model.balance;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BalanceRowMapper implements RowMapper<Balance>{

	@Override
	public Balance mapRow(ResultSet rs, int rowNum) throws SQLException {
		Balance balance = new Balance();
		balance.setBalance(rs.getBigDecimal("amount"));
		balance.setUsername(rs.getString("username"));
		balance.setTimeStamp(rs.getTimestamp("stamp"));
		return balance;
	}

}
