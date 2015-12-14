package com.walmart.services.cart.tests.parameter;

import java.util.List;

public class Cart {

	String id ;
	String type ;
	Boolean preview ;
	String customerId;
	
	Location location;
	List<String> storeIds;
	Integer itemCount;
	String currencyCode;
	Boolean hasSubmapTypeItem;

	ItemCountByType itemCountByType;
	SavedItemCountByType savedItemCountByType;
	Integer savedItemCount;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getPreview() {
		return preview;
	}
	public void setPreview(Boolean preview) {
		this.preview = preview;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
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
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Boolean getHasSubmapTypeItem() {
		return hasSubmapTypeItem;
	}
	public void setHasSubmapTypeItem(Boolean hasSubmapTypeItem) {
		this.hasSubmapTypeItem = hasSubmapTypeItem;
	}
	public ItemCountByType getItemCountByType() {
		return itemCountByType;
	}
	public void setItemCountByType(ItemCountByType itemCountByType) {
		this.itemCountByType = itemCountByType;
	}
	public SavedItemCountByType getSavedItemCountByType() {
		return savedItemCountByType;
	}
	public void setSavedItemCountByType(SavedItemCountByType savedItemCountByType) {
		this.savedItemCountByType = savedItemCountByType;
	}
	public Integer getSavedItemCount() {
		return savedItemCount;
	}
	public void setSavedItemCount(Integer savedItemCount) {
		this.savedItemCount = savedItemCount;
	}
		

}
		

