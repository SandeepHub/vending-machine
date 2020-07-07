package com.deloitte.vendingmachine.entity;

import java.util.Map;

public class Stock {
	
	private Map<Product,Integer> products;
	private Map<Currency,Integer> cash;
	
	public Map<Product, Integer> getProducts() {
		return products;
	}
	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}
	public void setCash(Map<Currency, Integer> cash) {
		this.cash = cash;
	}
	public Map<Currency, Integer> getCash() {
		return cash;
	}
	public void addcash(Currency feed) {
		if(this.cash.containsKey(feed))
			this.cash.put(feed, cash.get(feed)+1);
		else 
			this.cash.put(feed, 1);
	}
	
	public void addProduct(Product product) {
		if(this.products.containsKey(product))
			this.products.put(product, products.get(product)+1);
		else 
			this.products.put(product, 1);
	}
	
	
	

}
