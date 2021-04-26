package com.springtrader.SpringTrader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest
class UserStockDAOTest {
	
	
	@Test
	public void whenInjectInMemoryDataSource_thenReturnCorrectUserStockNumber() {
//	    DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//	      .addScript("classpath:jdbc/schema.sql")
//	      .addScript("classpath:jdbc/test-data.sql")
//	      .build();
//
//	    UserStockDAO userStockDAO = new UserStockDAO();
//	    userStockDAO.setDataSource(dataSource);
//
//	    assertEquals(3, userStockDAO.getUserPortfolioStocks("user", 0).size());
//	}
	}
}