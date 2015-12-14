package com.walmart.services.checkout.testng.buynowsuite.parameter;

import org.springframework.util.StringUtils;

public class CheckoutApiV2TestParameters {
	
	private String description;
	private PrepareOrderRequest prepareOrderRequest;
	private PlaceOrderRequest placeOrderRequest;
	private Integer errorCodeExpected;
	private String responseStatusExpected;
	private Boolean validateTotals;
	private Boolean validateFees;
	private Boolean validateShipPrice;
	private Boolean validateThresholdShipping;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PrepareOrderRequest getPrepareOrderRequest() {
		return prepareOrderRequest;
	}
	public void setPrepareOrderRequest(PrepareOrderRequest prepareOrderRequest) {
		this.prepareOrderRequest = prepareOrderRequest;
	}
	public Integer getErrorCodeExpected() {
		return errorCodeExpected;
	}
	public void setErrorCodeExpected(Integer errorCodeExpected) {
		this.errorCodeExpected = errorCodeExpected;
	}
	public String getResponseStatusExpected() {
		return responseStatusExpected;
	}
	public void setResponseStatusExpected(String responseStatusExpected) {
		this.responseStatusExpected = responseStatusExpected;
	}
	public PlaceOrderRequest getPlaceOrderRequest() {
		return placeOrderRequest;
	}
	public void setPlaceOrderRequest(PlaceOrderRequest placeOrderRequest) {
		this.placeOrderRequest = placeOrderRequest;
	}
	public Boolean getValidateTotals() {
		return validateTotals;
	}
	public void setValidateTotals(Boolean validateTotals) {
		this.validateTotals = validateTotals;
	}
	public Boolean getValidateFees() {
		return validateFees;
	}
	public void setValidateFees(Boolean validateFees) {
		this.validateFees = validateFees;
	}
	public Boolean getValidateShipPrice() {
		return validateShipPrice;
	}
	public void setValidateShipPrice(Boolean validateShipPrice) {
		this.validateShipPrice = validateShipPrice;
	}
	public Boolean getValidateThresholdShipping() {
		return validateThresholdShipping;
	}
	public void setValidateThresholdShipping(Boolean validateThresholdShipping) {
		this.validateThresholdShipping = validateThresholdShipping;
	}
	
	
	@Override
	public String toString() {
		
		if (!StringUtils.isEmpty(description)) {
			
			System.out.println("---------------------- Test Description {} "+description+"------------------------");
			return description + "\n";
		} 
		
		return super.toString();
	}

}
