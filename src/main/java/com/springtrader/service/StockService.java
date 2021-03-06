package com.springtrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springtrader.model.external.stock.IStock;
import com.springtrader.model.external.stock.Stock;


public class StockService {
	private int index = 0;
	
	@Autowired
	IStock[] stockProviders;
	
	public Stock getStockQuote(String symbol) {
		Stock stock =  stockProviders[index].getStockQuote(symbol);

		System.out.println("Stock provider index: " + index);
		nextIndex();
		return stock;
	}
	
	private void nextIndex() {
		index = (index +1) % stockProviders.length;
	}
}
