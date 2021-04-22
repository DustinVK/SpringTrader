package com.springtrader.SpringTrader.model.external.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Qualifier("mockSearch")
public class SearchMockDAO implements ISearchDAO {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<SearchResult> search(String term) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		SearchResult r = new SearchResult("AAPL", "Apple Inc.", "USD");
		results.add(r);
		r = new SearchResult("IBM", "International Business Machines Corporation", "USD");
		results.add(r);
		r = new SearchResult("TSLA", "Tesla Inc.", "USD");
		results.add(r);
		r = new SearchResult("N/A", "This shouldn't pass the service layer", "N/A");
		results.add(r);
		return results;
	}
	
}
