package com.springtrader.SpringTrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springtrader.SpringTrader.model.external.stock.IStockDAO;
import com.springtrader.SpringTrader.model.external.stock.Stock;

@Service
public class ExternalStockService {
	
	@Autowired
	@Qualifier("mockStock")
	IStockDAO stockClient;
	
	public Stock getStockQuote(String symbol) {
		return stockClient.getStockQuote(symbol);
	}
}
