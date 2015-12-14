package com.walmart.services.checkout.testng.buynowsuite.parameter;

public class ShippingInfo {

	public String fulfillmentType;
	public String fulfillmentOption;
	public String shipMethod;
	public Address address;
	public String addressValidationMode;
	
	public String getFulfillmentType() {
		return fulfillmentType;
	}
	public void setFulfillmentType(String fulfillmentType) {
		this.fulfillmentType = fulfillmentType;
	}
	public String getFulfillmentOption() {
		return fulfillmentOption;
	}
	public void setFulfillmentOption(String fulfillmentOption) {
		this.fulfillmentOption = fulfillmentOption;
	}
	public String getShipMethod() {
		return shipMethod;
	}
	public void setShipMethod(String shipMethod) {
		this.shipMethod = shipMethod;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getAddressValidationMode() {
		return addressValidationMode;
	}
	public void setAddressValidationMode(String addressValidationMode) {
		this.addressValidationMode = addressValidationMode;
	}
	
}
