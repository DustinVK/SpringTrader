package com.springtrader.model.external.stock;

public class Stock {
	private String symbol;
	private String price;
	private String changePercent;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getChangePercent() {
		return changePercent;
	}
	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
	}
}
