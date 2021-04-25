package com.springtrader.SpringTrader.model.portfolio;

import java.util.List;

public class Portfolio {
	private PortfolioMetaData metaData;
	private List<PortfolioRow> list;

	@Override
	public String toString() {
		if(this.metaData == null) {
			return "meta data: empty, List: " + listToString();
		}
		String r ="Name: "+ this.metaData.getName() +
				"ID: " + this.metaData.getId() +
				"User: " + this.metaData.getUsername() +
				"Portfolio: " + listToString() ;
		return r;
	}
	
	private String listToString() {
		if(this.list.isEmpty()) {
			return "List is empty...";
		}
		String r ="";
		for(PortfolioRow row : this.list) {
			r += row.getSymbol() +" amount:"+ row.getAmount();
		}
		return r;
	}
	
	public List<PortfolioRow> getList() {
		return list;
	}

	public void setList(List<PortfolioRow> list) {
		this.list = list;
	}

	public PortfolioMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(PortfolioMetaData metaData) {
		this.metaData = metaData;
	}

}
