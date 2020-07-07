package com.deloitte.vendingMachine.service;

import java.util.List;
import java.util.Set;

import com.deloitte.vendingMachine.entity.Currency;
import com.deloitte.vendingMachine.entity.Dispenser;
import com.deloitte.vendingMachine.entity.Product;

public interface VendingMachine {

	Set<Product> getAllProducts();

	String selectProduct(Product product);

	void insertDenomination(List<Currency> coins);

	Dispenser dispenseProductAndBalance();

	Double updatedVendingBalance();

	int stockCount(Product product);

	void addProduct(Product product);

	void addCash(Currency feed);

	
	
	  
	 

}
