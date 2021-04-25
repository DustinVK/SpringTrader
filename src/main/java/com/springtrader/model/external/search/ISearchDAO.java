package com.springtrader.model.external.search;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface ISearchDAO {
	List<SearchResult> search(String term);
}
