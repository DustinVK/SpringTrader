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
@Qualifier("PolygonSearch")
@PropertySource("application.properties")
public class PolygonSearch implements ISearch {
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${polygon.api.key}")
	private String key;

	
	public List<SearchResult> search(String term){
		final String uri = "https://api.polygon.io/v2/reference/tickers?search="+term+"&apiKey="+key;

		List<SearchResult> allResults = new ArrayList<SearchResult>();
		
	    String rawResult = restTemplate.getForObject(uri, String.class);
	    JSONObject root = new JSONObject(rawResult);
	    JSONArray results = root.getJSONArray("tickers");
	    
	    for(int i =0; i < results.length();i++) {
	    	JSONObject jsonResult = results.getJSONObject(i);
	    	SearchResult result = new SearchResult();
	    	result.setSymbol(jsonResult.getString("ticker"));
	    	result.setName(jsonResult.getString("name"));
	    	result.setCurrency(jsonResult.getString("currency"));
	    	allResults.add(result);
	    }
		System.out.println("Pulled search data from Polygon.io.");

		return allResults;
	}

}
