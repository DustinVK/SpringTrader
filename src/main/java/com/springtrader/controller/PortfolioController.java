package com.springtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.model.portfolio.NewPortfolio;
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
   
   @PostMapping("/users/{username}/portfolios")
   public String addPortfolio(@PathVariable("username") String userName, @RequestBody NewPortfolio portfolio) {
      return portfolioService.addPortfolio(userName, portfolio.getPortfolioName());
   }
   
   @DeleteMapping("/users/{username}/portfolios/{id}")
   public String deletePortfolio(@PathVariable("username") String userName, @PathVariable("id") String id) {
	//   List<Portfolio> portfolios = portfolioService.getPortfolios(userName);
      return "delete " + id;
   }

}
