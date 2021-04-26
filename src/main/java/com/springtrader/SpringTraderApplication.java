package com.springtrader;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.springtrader.model.external.search.AlphaVantageSearchDAO;
import com.springtrader.model.external.search.ISearchDAO;

import com.springtrader.service.SearchService;
import com.springtrader.util.PortfolioUtil;


@SpringBootApplication
public class SpringTraderApplication {
	

	
	public static void main(String[] args) {
//		PasswordEncoder pwe = new BCryptPasswordEncoder();
//		System.out.println(pwe.encode("pass"));
		SpringApplication.run(SpringTraderApplication.class, args);
	}

}
