package com.springtrader.SpringTrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.SpringTrader.model.internal.balance.Balance;
import com.springtrader.SpringTrader.service.BalanceService;

@RestController
public class BalanceController {
	
	@Autowired
	BalanceService balanceService;
	
	@RequestMapping("users/{username}/balance")
	public Balance getLatestUserBalance(@PathVariable("username") String userName){
	  	return balanceService.getLatestBalance(userName);
	}
}
