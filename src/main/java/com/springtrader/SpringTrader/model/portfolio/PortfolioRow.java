package com.springtrader.SpringTrader.model.portfolio;

import java.math.BigDecimal;

public class PortfolioRow {
	private String symbol;
	private BigDecimal amount;
	private BigDecimal price;
	private BigDecimal holdings;
	
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
	public BigDecimal getHoldings() {
		return holdings;
	}
	public void setHoldings(BigDecimal holdings) {
		this.holdings = holdings;
	}
}
