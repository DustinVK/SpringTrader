package com.springtrader.model.external.stock;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Qualifier("FinnHubStock")
@PropertySource("application.properties")
public class FinnHubStock implements IStock {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${finnhub.api.key}")
	private String key;

	@Override
	public Stock getStockQuote(String symbol) throws JSONException {
		symbol = symbol.toUpperCase();
		final String uri = "https://finnhub.io/api/v1/quote?symbol="+symbol+"&token="+key;
		String rawResult = restTemplate.getForObject(uri, String.class);
	
		Stock stockDTO = new Stock();
		try {
			JSONObject stockJSON = new JSONObject(rawResult);
		    BigDecimal currentPrice = stockJSON.getBigDecimal("c");
		    stockDTO.setPrice(String.valueOf(currentPrice));
		    BigDecimal previousPrice = stockJSON.getBigDecimal("pc");
		    stockDTO.setChangePercent(calculateChange(currentPrice,previousPrice));
		    stockDTO.setSymbol(symbol);
			System.out.println("Pulled stock data from Finnhub.io.");
		}
		catch(Exception e) {
			System.out.println("Error Getting Stock Quote for: "+symbol);
			System.out.println(e);
		}
	    
		return stockDTO;
	}
	
	private String calculateChange(BigDecimal current, BigDecimal previous) {
		BigDecimal dif = current.subtract(previous);
		BigDecimal change = dif.divide(previous,10, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
		return String.valueOf(change);
	}
	
}
