package com.springtrader.model.portfolio;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
 * A TradeRow represents one transaction in a Portfolio
 */
public class TradeRow {
	private String symbol;
	private BigDecimal amount; // positive for buys, negative for sells
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
	public Timestamp getStamp() {
		return stamp;
	}
	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}
}
