package com.walmart.services.checkout.testng.buynowsuite.parameter;

import java.util.List;
import java.util.UUID;

public class PrepareOrderResponse {
	
	UUID token;
	
	List<Item> items;
	
	List<Tax> taxes;
	
	List<Fee> fees;
	
	Totals totals;
	
	ShippingInfo shippingInfo;
	
	List<Error> errors;

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID token) {
		this.token = token;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Tax> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<Tax> taxes) {
		this.taxes = taxes;
	}

	public List<Fee> getFees() {
		return fees;
	}

	public void setFees(List<Fee> fees) {
		this.fees = fees;
	}

	public Totals getTotals() {
		return totals;
	}

	public void setTotals(Totals totals) {
		this.totals = totals;
	}

	public ShippingInfo getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(ShippingInfo shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}
