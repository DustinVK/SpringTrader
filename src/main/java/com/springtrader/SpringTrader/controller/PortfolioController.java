package com.springtrader.SpringTrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springtrader.SpringTrader.service.UserStockService;
import com.springtrader.SpringTrader.model.internal.userStock.UserStock;

@Controller
public class PortfolioController {
	
	@Autowired 
	UserStockService userStockService;
	
	   @RequestMapping("/users/{username}/portfolio")
	    public String getPortfolio(@PathVariable("username") String userName, Model model) {
		   List<UserStock> list = userStockService.getLatestUserStocks(userName);
		   model.addAttribute("userStocks", list);
	       return "portfolio";
	    }
}
