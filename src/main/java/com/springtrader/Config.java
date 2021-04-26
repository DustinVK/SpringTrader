package com.springtrader;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.springtrader.model.external.search.AlphaVantageSearchDAO;
import com.springtrader.model.external.search.ISearchDAO;
import com.springtrader.model.external.stock.IStockDAO;
import com.springtrader.model.external.stock.StockMockDAO;
import com.springtrader.model.portfolio.PortfolioDAO;
import com.springtrader.service.SearchService;
import com.springtrader.util.PortfolioUtil;

@Configuration
@ComponentScan
public class Config {
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
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
		//return NoOpPasswordEncoder.getInstance(); // plain text 'encoder' for testing
	}


	@Bean
	public ISearchDAO getAlphaSearchDAO() {
		return new AlphaVantageSearchDAO();
	}
	

	
	 // For tests
//	@Bean
//	public AlphaVantageStockDAO getAlphaStockDAO() {
//		return new AlphaVantageStockDAO();
//	}
	
}
