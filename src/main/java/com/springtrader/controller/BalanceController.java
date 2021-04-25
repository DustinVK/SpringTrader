package com.springtrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.model.user.balance.Balance;
import com.springtrader.service.BalanceService;

@RestController
public class BalanceController {
	
	@Autowired
	BalanceService balanceService;
	
	@RequestMapping("users/{username}/balance")
	public Balance getLatestUserBalance(@PathVariable("username") String userName){
	  	return balanceService.getLatestBalance(userName);
	}
}
