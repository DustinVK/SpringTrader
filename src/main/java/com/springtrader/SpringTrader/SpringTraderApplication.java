package com.springtrader.SpringTrader;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.springtrader.SpringTrader.model.internal.user.UserDAO;
import com.springtrader.SpringTrader.service.BalanceService;
import com.springtrader.SpringTrader.service.SearchService;
import com.springtrader.SpringTrader.service.StockService;

@SpringBootApplication
public class SpringTraderApplication {
	
	@Bean
	public static DataSource dataSource() {
	    return new EmbeddedDatabaseBuilder()
	      .setType(EmbeddedDatabaseType.H2)
	      .addScript("classpath:jdbc/schema.sql")
	      .addScript("classpath:jdbc/test-data.sql").build();
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public SearchService getSearchService() {
		return new SearchService();
	}
	
	@Bean
	public StockService getStockQuote() {
		return new StockService();
	}
	
	@Bean 
	public BalanceService getBalanceService() {
		return new BalanceService();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
		//return NoOpPasswordEncoder.getInstance();
	}

	
	public static void main(String[] args) {
//		PasswordEncoder pwe = new BCryptPasswordEncoder();
//		System.out.println(pwe.encode("pass"));
		SpringApplication.run(SpringTraderApplication.class, args);
	}

}
