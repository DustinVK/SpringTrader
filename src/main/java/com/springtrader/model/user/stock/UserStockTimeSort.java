package com.springtrader.model.user.stock;

import java.util.Comparator;

public class UserStockTimeSort implements Comparator<UserStock> {
	@Override
	public int compare(UserStock o1, UserStock o2) {
		if(o1 != null && o2 != null) {
//			return o2.getStamp().compareTo(o1.getStamp());
			return 1;
		}
		else return 0;
	}
}