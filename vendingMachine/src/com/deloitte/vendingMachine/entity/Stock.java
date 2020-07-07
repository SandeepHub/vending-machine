package com.deloitte.vendingMachine.entity;

import java.util.HashMap;
import java.util.Map;

public class Stock {
	
	private Map<Product,Integer> products;
	private Map<Currency,Integer> cash;
	
	//Needs to be removed, added for simplicity purpose can be done at Test setup as well 
	{  
       Map<Product,Integer> initProducts =new HashMap<>(); 
       initProducts.put(new Product("Cold Drink",25.00),4);
       initProducts.put(new Product("Chocolate",50.00),5);
       initProducts.put(new Product("Cookies",10.00),7);
       initProducts.put(new Product("Snacks",5.00),4);
       products = initProducts;
       
       Map<Currency,Integer> initCash = new HashMap<>();
       initCash.put(Currency.DIME, 5);
       initCash.put(Currency.DOLLAR, 2);
       initCash.put(Currency.NICKLE, 7);
       initCash.put(Currency.QUARTER, 4);
       initCash.put(Currency.PENNY, 24);
       cash= initCash; 
    } 
	
	
	
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
