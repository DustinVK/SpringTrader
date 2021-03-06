package com.springtrader.model.external.stock;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



//@Component
//@Qualifier("alphaVantageStock")
@PropertySource("application.properties")
public class AlphaVantageStock implements IStock {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${alphavantage.api.key}")
	private String key;

	@Override
	public Stock getStockQuote(String symbol) throws JSONException {
		final String uri = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="+symbol+"&apikey="+key;
		String rawResult = restTemplate.getForObject(uri, String.class);
		Stock stockDTO = new Stock();
		try {
			JSONObject stockJSON = new JSONObject(rawResult).getJSONObject("Global Quote");
			    stockDTO.setChangePercent(stockJSON.getString("10. change percent"));
			    stockDTO.setPrice(stockJSON.getString("05. price"));
			    stockDTO.setSymbol(stockJSON.getString("01. symbol"));
				System.out.println("Pulled stock data from AlphaVantage.co.");
		}
		catch(Exception e) {
			System.out.println("Error Getting Stock Quote for: "+symbol);
			System.out.println(e);
		}
	    
		return stockDTO;
	}

}
