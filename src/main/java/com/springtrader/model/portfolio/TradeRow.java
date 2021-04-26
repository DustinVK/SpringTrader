package com.springtrader.model.portfolio;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TradeRow {
	private String symbol;
	private BigDecimal amount;
	private BigDecimal price;
	private Timestamp stamp;
	
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
//	public BigDecimal getHoldings() {
//		return holdings;
//	}
//	public void setHoldings(BigDecimal holdings) {
//		this.holdings = holdings;
//	}
	public Timestamp getStamp() {
		return stamp;
	}
	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}
}
