package com.walmart.services.checkout.testng.buynowsuite.parameter;

public class PlaceOrderRequest {
	
	String token;
	PaymentInfo paymentInfo;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

}
