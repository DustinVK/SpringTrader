package com.springtrader.model.external.search;


public class SearchResult {

	private String symbol;

	private String name;
	
	private String currency;
	


	public SearchResult(String symbol, String name, String currency) {
		this.symbol = symbol;
		this.name = name;
		this.currency = currency;
	}
	
	public SearchResult() {
		
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
