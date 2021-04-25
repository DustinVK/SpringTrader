package com.springtrader.model.portfolio;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PortfolioRowMapper implements RowMapper<PortfolioRow>{

	@Override
	public PortfolioRow mapRow(ResultSet rs, int rowNum) throws SQLException {
		PortfolioRow row = new PortfolioRow();
		BigDecimal amount = rs.getBigDecimal("amount");
		BigDecimal price = rs.getBigDecimal("price");
		row.setAmount(amount);
		row.setPrice(price);
		row.setSymbol(rs.getString("symbol"));
		row.setHoldings(calculateHoldings(price, amount));
		return row;
	}
	
	private BigDecimal calculateHoldings(BigDecimal price, BigDecimal amount) {
		return price.multiply(amount);
	}

}
