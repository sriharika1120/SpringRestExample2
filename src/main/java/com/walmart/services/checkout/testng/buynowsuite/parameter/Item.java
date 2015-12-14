package com.walmart.services.checkout.testng.buynowsuite.parameter;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
	
	String itemId;
	int sellerId;
    int qty;
    BigDecimal unitPrice;
    Date expectedShipTimestamp;
    Date expectedDeliveryTimestamp;
    
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Date getExpectedShipTimestamp() {
		return expectedShipTimestamp;
	}
	public void setExpectedShipTimestamp(Date expectedShipTimestamp) {
		this.expectedShipTimestamp = expectedShipTimestamp;
	}
	public Date getExpectedDeliveryTimestamp() {
		return expectedDeliveryTimestamp;
	}
	public void setExpectedDeliveryTimestamp(Date expectedDeliveryTimestamp) {
		this.expectedDeliveryTimestamp = expectedDeliveryTimestamp;
	}

}
