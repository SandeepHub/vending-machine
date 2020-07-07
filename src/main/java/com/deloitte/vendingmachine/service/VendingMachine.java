package com.deloitte.vendingmachine.service;

import java.util.List;
import java.util.Set;

import com.deloitte.vendingmachine.entity.Currency;
import com.deloitte.vendingmachine.entity.Dispenser;
import com.deloitte.vendingmachine.entity.Product;
import com.deloitte.vendingmachine.entity.Stock;

public interface VendingMachine {

	Set<Product> getAllProducts();

	String selectProduct(Product product);

	void insertDenomination(List<Currency> coins);

	Dispenser dispenseProductAndBalance();

	Double updatedVendingBalance();

	int stockCount(Product product);

	void addProduct(Product product);

	void addCash(Currency feed);
	
	void setStock(Stock stock);
	
	  
	 

}
