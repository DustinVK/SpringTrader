package com.springtrader.SpringTrader.model.portfolio;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PortfolioMetaRowMapper implements RowMapper<PortfolioMetaData> {

	@Override
	public PortfolioMetaData mapRow(ResultSet rs, int rowNum) throws SQLException {
		PortfolioMetaData metaData = new PortfolioMetaData();
		metaData.setId(rs.getLong("id"));
		metaData.setName(rs.getString("name"));
		metaData.setUsername(rs.getString("username"));		
		return metaData;
	}

}
