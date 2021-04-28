package com.springtrader.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.springtrader.model.external.search.ISearch;
import com.springtrader.model.external.search.SearchResult;


public class SearchService {
	
	//final int ALPHAVANTAGE = 0;
	//final int MOCK = 1;
	
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
		if(index < searchProviders.length-1) {
			index ++;
		} else {
			index = 0;
		}
		
	}
	

}
