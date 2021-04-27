package com.springtrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringTraderApplication {
	

	
	public static void main(String[] args) {
//		PasswordEncoder pwe = new BCryptPasswordEncoder();
//		System.out.println(pwe.encode("pass"));
		SpringApplication.run(SpringTraderApplication.class, args);
	}

}
