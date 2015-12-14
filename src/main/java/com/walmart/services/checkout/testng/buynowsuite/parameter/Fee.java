package com.walmart.services.checkout.testng.buynowsuite.parameter;

import java.math.BigDecimal;

public class Fee {
	
	String type;
	BigDecimal amount;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
