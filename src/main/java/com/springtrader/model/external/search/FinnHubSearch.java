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

@Component // comment component and qualifier out for real search 
@Qualifier("FinnHub")
@PropertySource("application.properties")
public class FinnHubSearch implements ISearch {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${finnhub.api.key}")
	private String key;

	
	public List<SearchResult> search(String term){
		final String uri = "https://finnhub.io/api/v1/search?q="+term+"&token="+key;

		List<SearchResult> allResults = new ArrayList<SearchResult>();
		
	    String rawResult = restTemplate.getForObject(uri, String.class);
	    JSONObject root = new JSONObject(rawResult);
	    JSONArray results = root.getJSONArray("result");
	    
	    for(int i =0; i < results.length();i++) {
	    	JSONObject jsonResult = results.getJSONObject(i);
	    	SearchResult result = new SearchResult();
	    	result.setSymbol(jsonResult.getString("symbol"));
	    	result.setName(jsonResult.getString("description"));
	    	result.setCurrency("USD");
	    	allResults.add(result);
	    }
		System.out.println("Pulled search data from Finnhub.io.");
	    
		return allResults;
	}


}
