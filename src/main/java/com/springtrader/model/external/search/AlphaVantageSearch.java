package com.springtrader.model.external.search;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component 
@Qualifier("AlphaVantage")
@PropertySource("application.properties")
public class AlphaVantageSearch implements ISearch {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${alphavantage.api.key}")
	private String key;

	@Override
	public List<SearchResult> search(String term) {
		final String uri = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords="+term+"&apikey="+key;

		List<SearchResult> allResults = new ArrayList<SearchResult>();
		
	    String rawResult = restTemplate.getForObject(uri, String.class);
	    JSONObject root = new JSONObject(rawResult);

	    JSONArray results = root.getJSONArray("bestMatches");
	    
	    for(int i =0; i < results.length();i++) {
	    	JSONObject jsonResult = results.getJSONObject(i);
	    	SearchResult result = new SearchResult();
	    	result.setSymbol(jsonResult.getString("1. symbol"));
	    	result.setName(jsonResult.getString("2. name"));
	    	result.setCurrency(jsonResult.getString("8. currency"));
	    	allResults.add(result);
	    }
		System.out.println("Pulled seach data from AlphaVantage.co.");

		return allResults;
	}
	
}
