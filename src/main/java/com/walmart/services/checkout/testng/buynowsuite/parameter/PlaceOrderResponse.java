package com.walmart.services.checkout.testng.buynowsuite.parameter;

import java.util.List;

public class PlaceOrderResponse {
	
	String orderId;
	List<Error> errors;

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
