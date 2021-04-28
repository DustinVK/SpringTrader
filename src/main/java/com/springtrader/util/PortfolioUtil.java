package com.springtrader.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springtrader.model.external.stock.IStock;
import com.springtrader.model.external.stock.MockStock;
import com.springtrader.model.portfolio.Portfolio;
import com.springtrader.model.portfolio.PortfolioMetaData;
import com.springtrader.model.portfolio.PortfolioRow;
import com.springtrader.model.portfolio.TradeRow;
import com.springtrader.service.ExternalStockService;

@Component
public class PortfolioUtil {
	
	final String CASH_IN = "getUserPortfolioCashIn";
	final String CASH_OUT = "getUserPortfolioCashOut";
	private int index = 0;

	private ExternalStockService stockService;

	public void setStockService(ExternalStockService stockService) {
		this.stockService = stockService;
	}

	public Portfolio getPortfolio(List<TradeRow> list) {		
		HashMap<String, BigDecimal> map = getPortfolioHashMap(list);
		Portfolio portfolio = mapToPortfolio(map);
		return portfolio;
		
	}

	// returns a hashmap with key of stock symbol and it's value of total amount held in portfolio
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
	private Portfolio mapToPortfolio(HashMap<String, BigDecimal> map) {
		List<PortfolioRow> rowList = new ArrayList<>();
		
		PortfolioMetaData metaData = getMetaWithCashValues(map);
		map.remove(CASH_IN);
		map.remove(CASH_OUT);
		
		BigDecimal total = getRowAndTotal(map, rowList);
		metaData.setTotalHoldings(total);
		metaData.setCashChange(calculateCashChange(metaData, total));
		if(metaData.getCashIn().compareTo(BigDecimal.ZERO) > 0) {
			metaData.setPercentChange(calculatePercentChange(metaData));
		} else {
			metaData.setPercentChange(BigDecimal.ZERO);
		}
		
		Portfolio portfolio = new Portfolio();
		portfolio.setMetaData(metaData);
		portfolio.setList(rowList);
		return portfolio;
	}

	private BigDecimal calculateCashChange(PortfolioMetaData metaData, BigDecimal total) {
		return total.subtract(metaData.getCashIn().subtract(metaData.getCashOut()));
	}
	
	private BigDecimal calculatePercentChange(PortfolioMetaData metaData) {
		BigDecimal pctDecimal = metaData.getCashChange().divide(metaData.getCashIn(), RoundingMode.HALF_UP);
		return pctDecimal.multiply(new BigDecimal(100));
	}


	private BigDecimal getRowAndTotal(HashMap<String, BigDecimal> map, List<PortfolioRow> rowList) {
		BigDecimal total = new BigDecimal(0);
		for(Map.Entry<String, BigDecimal> element : map.entrySet()) {
			PortfolioRow row = new PortfolioRow();
			String symbol = element.getKey();
			row.setSymbol(symbol);
			row.setAmount(map.get(symbol));
			BigDecimal price = new BigDecimal(stockService.getStockQuote(symbol).getPrice());
			row.setPrice(price);
			row.setHoldings(getHoldings(map, symbol, price));
			rowList.add(row);
			total = total.add(getHoldings(map, symbol, price));
		}
		return total;
	}
	// multiplies price and amount of asset held to get total holdings in dollar amount value 
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
