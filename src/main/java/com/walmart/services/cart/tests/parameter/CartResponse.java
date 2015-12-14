package com.walmart.services.cart.tests.parameter;

public class CartResponse {

	Boolean checkoutable;
	Cart cart;
	SflList sflList;
	String orchTimeout;
	
	public Boolean getCheckoutable() {
		return checkoutable;
	}
	public void setCheckoutable(Boolean checkoutable) {
		this.checkoutable = checkoutable;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public SflList getSflList() {
		return sflList;
	}
	public void setSflList(SflList sflList) {
		this.sflList = sflList;
	}
	public String getOrchTimeout() {
		return orchTimeout;
	}
	public void setOrchTimeout(String orchTimeout) {
		this.orchTimeout = orchTimeout;
	}
	
	
}
