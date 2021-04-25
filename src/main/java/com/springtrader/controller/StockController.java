package com.springtrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.model.external.stock.Stock;
import com.springtrader.service.ExternalStockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	ExternalStockService service;
	
	@RequestMapping("/{symbol}")
	public Stock getStockQuote(@PathVariable String symbol) {
		return service.getStockQuote(symbol);
	}

}
