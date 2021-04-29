package com.springtrader.model.portfolio;

import java.util.List;

public class TransactionHistory {
	private String name;
	private List<TradeRow> transactionList;
	
	public List<TradeRow> getList() {
		return transactionList;
	}
	public void setList(List<TradeRow> list) {
		this.transactionList = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
