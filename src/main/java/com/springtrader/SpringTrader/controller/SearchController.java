package com.springtrader.SpringTrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.SpringTrader.model.external.search.SearchResult;
import com.springtrader.SpringTrader.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	SearchService service;
	
	@RequestMapping("/{entry}")
	public List<SearchResult> search(@PathVariable String entry, Model model) {
		return service.search(entry);
	}

}
