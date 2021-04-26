package com.springtrader.model.external.stock;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mockStock")

public class StockMockDAO implements IStockDAO{

	@Override
	public Stock getStockQuote(String symbol) {
		if(symbol == null) {
			System.out.println("null symbol input in mock stock ");
		}
		Stock stockDTO = new Stock();
		stockDTO.setPrice("133.5900");
		stockDTO.setSymbol(symbol);
		stockDTO.setChangePercent("0.7618%");
		return stockDTO;
	}

}
