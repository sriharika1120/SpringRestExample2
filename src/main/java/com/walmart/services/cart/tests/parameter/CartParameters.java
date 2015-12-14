package com.walmart.services.cart.tests.parameter;

public class CartParameters {

	private String description;
	private CartRequest cartRequest;
	
	//expected Parameters
	
	Integer itemCountExp;
	Integer savedItemCountExp;
	String currencyCountExp;
	String countryCodeExp;
	String	sflstatus;

	public String getSflstatus() {
		return sflstatus;
	}

	public void setSflstatus(String sflstatus) {
		this.sflstatus = sflstatus;
	}

	public String getCurrencyCountExp() {
		return currencyCountExp;
	}

	public void setCurrencyCountExp(String currencyCountExp) {
		this.currencyCountExp = currencyCountExp;
	}

	public String getCountryCodeExp() {
		return countryCodeExp;
	}

	public void setCountryCodeExp(String countryCodeExp) {
		this.countryCodeExp = countryCodeExp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getItemCountExp() {
		return itemCountExp;
	}

	public void setItemCountExp(Integer itemCountExp) {
		this.itemCountExp = itemCountExp;
	}

	public Integer getSavedItemCountExp() {
		return savedItemCountExp;
	}

	public void setSavedItemCountExp(Integer savedItemCountExp) {
		this.savedItemCountExp = savedItemCountExp;
	}

	public CartRequest getCartRequest() {
		return cartRequest;
	}

	public void setCartRequest(CartRequest cartRequest) {
		this.cartRequest = cartRequest;
	}

	public static Object getCartParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
