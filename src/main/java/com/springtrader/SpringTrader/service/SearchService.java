package com.springtrader.SpringTrader.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.springtrader.SpringTrader.model.external.search.ISearchDAO;
import com.springtrader.SpringTrader.model.external.search.SearchResult;


public class SearchService {
	
	final int ALPHAVANTAGE = 0;
	final int MOCK = 1;
	
	@Autowired
	ISearchDAO[] searchDaos;
	
	
	public List<SearchResult> search(String entry) {
		List<SearchResult> list = searchDaos[MOCK].search(entry);
		List<SearchResult> returnList = new ArrayList<SearchResult>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getCurrency().equals("USD")) {
				returnList.add(list.get(i));
			}
		}
	
		return returnList;
	}

	

}
