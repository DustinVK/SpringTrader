package com.springtrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springtrader.model.external.stock.IStockDAO;
import com.springtrader.model.external.stock.Stock;

@Service
public class ExternalStockService {
	
	@Autowired
	@Qualifier("mockStock")
	IStockDAO stockClient;
	
	public Stock getStockQuote(String symbol) {
		return stockClient.getStockQuote(symbol);
	}
}
