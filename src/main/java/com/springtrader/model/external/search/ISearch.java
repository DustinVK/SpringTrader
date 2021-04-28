package com.springtrader.model.external.search;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface ISearch {
	List<SearchResult> search(String term);
}
