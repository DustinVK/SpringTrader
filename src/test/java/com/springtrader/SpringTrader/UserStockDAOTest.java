package com.springtrader.SpringTrader;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;

import com.springtrader.SpringTrader.model.internal.userStock.UserStockDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class UserStockDAOTest {
	
	
	@Test
	public void whenInjectInMemoryDataSource_thenReturnCorrectUserStockNumber() {
	    DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
	      .addScript("classpath:jdbc/schema.sql")
	      .addScript("classpath:jdbc/test-data.sql")
	      .build();

	    UserStockDAO userStockDAO = new UserStockDAO();
	    userStockDAO.setDataSource(dataSource);

	    assertEquals(3, userStockDAO.getUserStocks("user").size());
	}
	
}