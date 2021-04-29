package com.springtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.springtrader.model.portfolio.NewPortfolio;
import com.springtrader.model.portfolio.Portfolio;
import com.springtrader.model.portfolio.TradeRow;
import com.springtrader.model.portfolio.TransactionHistory;
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
   
   // Using class for input 
   @PostMapping("/users/{username}/portfolios")
   public String addPortfolio(@PathVariable("username") String userName, @RequestBody NewPortfolio portfolio) {
      return portfolioService.addPortfolio(userName, portfolio.getPortfolioName());
   }
   
   @RequestMapping("/users/{username}/portfolios/{id}")
   public TransactionHistory showTransactions(@PathVariable("username") String username, @PathVariable("id") long id) {
      return portfolioService.getTransactions(username, id);
   }
   
   @DeleteMapping("/users/{username}/portfolios/{id}")
   public String deletePortfolio(@PathVariable("username") String username, Authentication auth, @PathVariable("id") long id) {
	  if(auth.getName().equals(username)) {
		  return portfolioService.deletePortfolio(id, username);
	  }
	  	return "You can't do that...";
     
   }
   
   // Using class for input 
   @PostMapping("/users/{username}/portfolios/{id}")
   public String addTransaction(@PathVariable("username") String username, Authentication auth, @PathVariable("id") long id, @RequestBody TradeRow transaction) {
	   if(auth.getName().equals(username)) {
		      return portfolioService.addTransaction(id, username, transaction);
		  }
		  	return "You can't do that...";
   }
   


}
