package com.springtrader.SpringTrader.model.external.stock;

import org.springframework.stereotype.Component;

@Component
public interface IStockDAO {
	Stock getStockQuote(String symbol);
}
