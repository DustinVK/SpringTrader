package com.springtrader.model.portfolio;

import java.math.BigDecimal;

public class PortfolioMetaData {
	private long id;
	private	String username;
	private String name;
	private BigDecimal cashIn;
	private BigDecimal cashOut;
	private BigDecimal totalHoldings;
	private BigDecimal cashChange;
	private BigDecimal percentChange;
	
	
	public BigDecimal getCashIn() {
		return cashIn;
	}
	public void setCashIn(BigDecimal principal) {
		this.cashIn = principal;
	}
	public BigDecimal getTotalHoldings() {
		return totalHoldings;
	}
	public void setTotalHoldings(BigDecimal totalHoldings) {
		this.totalHoldings = totalHoldings;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(BigDecimal percentChange) {
		this.percentChange = percentChange;
	}
	public BigDecimal getCashOut() {
		return cashOut;
	}
	public void setCashOut(BigDecimal cashOut) {
		this.cashOut = cashOut;
	}
	public BigDecimal getCashChange() {
		return cashChange;
	}
	public void setCashChange(BigDecimal cashChange) {
		this.cashChange = cashChange;
	}
}
