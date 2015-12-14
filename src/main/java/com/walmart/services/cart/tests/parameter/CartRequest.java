package com.walmart.services.cart.tests.parameter;

import java.util.List;
import java.util.UUID;


public class CartRequest {

	 Location location;
	 List<String> storeIds;
	 UUID customerId;
	 String customerType;
	 
	 
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<String> getStoreIds() {
		return storeIds;
	}
	public void setStoreIds(List<String> storeIds) {
		this.storeIds = storeIds;
	}
	public UUID getCustomerId() {
		return customerId;
	}
	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	 
	 
	
}
