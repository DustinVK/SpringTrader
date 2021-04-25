package com.springtrader.model.user.stock;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Comparator;

public class UserStock {
	private String username;
	private long portfolioId;
	private String symbol;
	private BigDecimal amount;
	private BigDecimal price;
	private Timestamp stamp;
	
	public long getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(long portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Timestamp getStamp() {
		return stamp;
	}
	public void setStamp(Timestamp timeStamp) {
		this.stamp = timeStamp;
	}
	
	@Override
	public String toString() {
		return "USERSTOCK:\n SYMBOL: \n"+"Username: "+this.getUsername()+"";
	}

}