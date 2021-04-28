package com.springtrader.model.external.stock;

import org.springframework.stereotype.Component;

@Component
public interface IStock {
	Stock getStockQuote(String symbol);
}
