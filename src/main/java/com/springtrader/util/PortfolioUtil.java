package com.springtrader.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springtrader.model.external.stock.IStockDAO;
import com.springtrader.model.external.stock.StockMockDAO;
import com.springtrader.model.portfolio.Portfolio;
import com.springtrader.model.portfolio.PortfolioMetaData;
import com.springtrader.model.portfolio.PortfolioRow;
import com.springtrader.model.portfolio.TradeRow;

@Service
public class PortfolioUtil {
	
	final String CASH_IN = "getUserPortfolioCashIn";
	final String CASH_OUT = "getUserPortfolioCashOut";

	
	private IStockDAO stockDAO = new StockMockDAO();
	
	
	public Portfolio getPortfolio(List<TradeRow> list) {
		
		HashMap<String, BigDecimal> map = getPortfolioHashMap(list);
		Portfolio portfolio = getPortfolio(map);
		return portfolio;
		
	}

	// returns a hashmap with key of stock symbol and value of total amount in portfolio
	private HashMap<String, BigDecimal> getPortfolioHashMap(List<TradeRow> list) {
		HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		BigDecimal cashIn = new BigDecimal(0);
		BigDecimal cashOut = new BigDecimal(0);
		for(TradeRow row : list) {
			String symbol = row.getSymbol();
			BigDecimal amount = row.getAmount();
			if(!map.containsKey(symbol)) {
				map.put(symbol, amount);
			} else {
				BigDecimal mapAmount = map.get(row.getSymbol());
				mapAmount = mapAmount.add(amount);
				map.put(symbol, mapAmount);
			}
			if(amount.signum() < 0) {
				cashOut = cashOut.add(amount.abs().multiply(row.getPrice()));
			}
			else {
				cashIn = cashIn.add(row.getAmount().multiply(row.getPrice()));
			}
		}
		map.put(CASH_IN, cashIn);
		map.put(CASH_OUT, cashOut);
		return map;
	}
	
	// takes portfolio hashmap and returns a Portfolio object
	private Portfolio getPortfolio(HashMap<String, BigDecimal> map) {
		List<PortfolioRow> rowList = new ArrayList<>();
		PortfolioMetaData metaData = getMetaWithCashValues(map);
		map.remove(CASH_IN);
		map.remove(CASH_OUT);
		
		BigDecimal total = new BigDecimal(0);
		for(Map.Entry<String, BigDecimal> element : map.entrySet()) {
			PortfolioRow row = new PortfolioRow();
			String symbol = element.getKey();
			row.setSymbol(symbol);
			row.setAmount(map.get(symbol));
			BigDecimal price = new BigDecimal(stockDAO.getStockQuote(symbol).getPrice());
			row.setPrice(price);
			row.setHoldings(getHoldings(map, symbol, price));
			rowList.add(row);
			total = total.add(getHoldings(map, symbol, price));
		}
		metaData.setTotalHoldings(total);
		metaData.setCashChange(total.subtract(metaData.getCashIn()));
		Portfolio portfolio = new Portfolio();
		portfolio.setMetaData(metaData);
		portfolio.setList(rowList);
		return portfolio;
	}

	private BigDecimal getHoldings(HashMap<String, BigDecimal> map, String symbol, BigDecimal price) {
		return price.multiply(map.get(symbol));
	}
	
	// returns a PortfolioMetaData object with cashIn and cashOut values set 
	private PortfolioMetaData getMetaWithCashValues(HashMap<String, BigDecimal> map) {
		PortfolioMetaData metaData = new PortfolioMetaData();
		metaData.setCashIn(map.get(CASH_IN));
		metaData.setCashOut(map.get(CASH_OUT));
		return metaData;
	}

}
