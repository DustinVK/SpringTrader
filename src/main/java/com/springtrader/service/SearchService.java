package com.springtrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.springtrader.model.external.search.ISearch;
import com.springtrader.model.external.search.SearchResult;


public class SearchService {
	private int index = 0;
	// register services 
	@Autowired
	ISearch[] searchProviders;
	
	public List<SearchResult> search(String entry) {
		List<SearchResult> list = searchProviders[index].search(entry);
		System.out.println("Searching index: " + index);
		nextIndex();
		if(list.isEmpty()) {
			System.out.println("empty results");
		}
		return list;
	}

	private void nextIndex() {
		index = (index +1) % searchProviders.length;
	}
}
