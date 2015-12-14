package com.walmart.services.checkout.testng.buynowsuite.parameter;

import java.math.BigDecimal;

public class Totals {
	
	BigDecimal subTotal;
	BigDecimal shippingTotal;
	BigDecimal taxTotal;
	BigDecimal feesTotal;
	BigDecimal grandTotal;
	
	
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getShippingTotal() {
		return shippingTotal;
	}
	public void setShippingTotal(BigDecimal shippingTotal) {
		this.shippingTotal = shippingTotal;
	}
	public BigDecimal getTaxTotal() {
		return taxTotal;
	}
	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}
	public BigDecimal getFeesTotal() {
		return feesTotal;
	}
	public void setFeesTotal(BigDecimal feesTotal) {
		this.feesTotal = feesTotal;
	}
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

}
