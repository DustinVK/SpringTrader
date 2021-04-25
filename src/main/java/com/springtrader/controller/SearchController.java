package com.springtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.model.external.search.SearchResult;
import com.springtrader.service.SearchService;

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
