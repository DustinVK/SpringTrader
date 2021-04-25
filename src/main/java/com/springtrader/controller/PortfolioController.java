package com.springtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.model.portfolio.Portfolio;
import com.springtrader.service.PortfolioService;

@RestController
public class PortfolioController {
	@Autowired
	PortfolioService portfolioService;
	
	
   @RequestMapping("/users/{username}/portfolios")
    public List<Portfolio> getPortfolio(@PathVariable("username") String userName) {
	   List<Portfolio> portfolios = portfolioService.getPortfolios(userName);
       return portfolios;
    }

}
