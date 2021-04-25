package com.springtrader.model.user.balance;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Balance {
	private BigDecimal amount;
	private String username;
	private Timestamp timeStamp;
	
	public BigDecimal getBalance() {
		return amount;
	}
	public void setBalance(BigDecimal balance) {
		this.amount = balance;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
