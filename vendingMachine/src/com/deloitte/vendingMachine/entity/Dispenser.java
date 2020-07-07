package com.deloitte.vendingMachine.entity;

import java.util.List;

public class Dispenser {
	
	Product product;
	List<Currency> currencies;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Currency> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
	
	

}
