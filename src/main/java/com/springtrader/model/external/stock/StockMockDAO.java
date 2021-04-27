package com.springtrader.model.external.stock;

import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mockStock")

public class StockMockDAO implements IStockDAO{
	Random random = new Random();
	@Override
	public Stock getStockQuote(String symbol) {
		int rand = random.nextInt(800) +1;
		float price = random.nextFloat() + rand;
		if(symbol == null) {
			System.out.println("null symbol input in mock stock ");
		}
		Stock stockDTO = new Stock();
		stockDTO.setPrice(String.valueOf(price));
		stockDTO.setSymbol(symbol);
		stockDTO.setChangePercent("0.7618%");
		return stockDTO;
	}

}
