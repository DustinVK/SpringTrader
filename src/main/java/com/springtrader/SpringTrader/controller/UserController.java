package com.springtrader.SpringTrader.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.SpringTrader.model.balance.Balance;
import com.springtrader.SpringTrader.model.external.stock.IStockDAO;
import com.springtrader.SpringTrader.model.portfolio.Portfolio;
import com.springtrader.SpringTrader.model.portfolio.PortfolioRow;
import com.springtrader.SpringTrader.model.user.User;
import com.springtrader.SpringTrader.model.user.UserPrincipal;
import com.springtrader.SpringTrader.model.userStock.UserStock;
import com.springtrader.SpringTrader.service.BalanceService;
import com.springtrader.SpringTrader.service.PortfolioService;
import com.springtrader.SpringTrader.service.ExternalStockService;
import com.springtrader.SpringTrader.service.UserStockService;

@Controller
public class UserController {
	@Autowired
	PortfolioService portfolioService;
	
	@Autowired
	UserStockService uss;
	
	@Autowired 
	ExternalStockService stockService;
	
	@RequestMapping("/users/{username}")
    public String getUserInfo(@PathVariable("username") String userName, Authentication auth, Model model){
	  System.out.println(auth.getName());
	  return "user";
    }
	

   
//  @GetMapping("/listHeaders")
//  public ResponseEntity<String> listAllHeaders(
//    @RequestHeader Map<String, String> headers) {
//      headers.forEach((key, value) -> {
//          System.out.println(String.format("Header '%s' = %s", key, value));
//      });
//
//      return new ResponseEntity<String>(
//        String.format("Listed %d headers", headers.size()), HttpStatus.OK);
//  }
}
