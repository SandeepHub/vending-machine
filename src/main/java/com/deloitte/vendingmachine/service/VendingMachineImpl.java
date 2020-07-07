package com.deloitte.vendingmachine.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.deloitte.vendingmachine.exceptions.DispenseException;
import com.deloitte.vendingmachine.entity.Currency;
import com.deloitte.vendingmachine.entity.Dispenser;
import com.deloitte.vendingmachine.entity.Product;
import com.deloitte.vendingmachine.entity.Stock;

public class VendingMachineImpl implements VendingMachine {

	private Product currentProduct;
	private double currentBalance;
	private Stock stock;
	private List<Currency> amountEntered;

	public VendingMachineImpl() {
		super();
	}

	@Override
	public Set<Product> getAllProducts() {
		return stock.getProducts().keySet();
	}

	@Override
	public String selectProduct(Product product) {
		Product selectedProduct = stock.getProducts().keySet().stream()
				.filter(p -> p.getName().equalsIgnoreCase(product.getName())).findAny()
				.orElseThrow(() -> new DispenseException("Product not available"));
		this.currentProduct = selectedProduct;
		return "SUCCESS";

	}

	@Override
	public void insertDenomination(List<Currency> coins) {
		this.amountEntered = coins;
		Double totalDenomiation = coins.stream().mapToDouble(Currency::getvalue).sum();
		if (currentProduct.getPrice() > totalDenomiation)
			throw new DispenseException("Insuffient coins");
		else
			currentBalance = totalDenomiation - currentProduct.getPrice();
	}
	@Override
	public Dispenser dispenseProductAndBalance() {
		List<Currency> denominations;
		Dispenser response = new Dispenser();
		if (currentBalance == 0)
			denominations = null;
		
		denominations = computeBalance();

		
		response.setCurrencies(denominations);
		response.setProduct(currentProduct);
		int count = stock.getProducts().get(currentProduct) - 1;
		if (count == 0)
			stock.getProducts().remove(currentProduct);
		else
			stock.getProducts().put(currentProduct, count);
		
		return response;

	}

	private List<Currency> computeBalance() {
		double vendingBalance = stock.getCash().entrySet().stream()
				.mapToDouble(e -> e.getKey().getvalue() * e.getValue()).sum();
		if (currentBalance > vendingBalance)
			throw new DispenseException("Insufficent balance in Vending machine");

		List<Double> availableCoins = new ArrayList<>();
		List<Currency> dispenseCoins = new ArrayList<>();
		for (Entry<Currency, Integer> entry : stock.getCash().entrySet()) {
			int count = entry.getValue();
			while (count > 0) {
				availableCoins.add(entry.getKey().getvalue());
				count--;
			}
		}
		Collections.sort(availableCoins);
		double checker = currentBalance;
		
			for (int i = availableCoins.size()-1; i > 0; i--) {
				if(checker ==0)
					break;
				if (availableCoins.get(i) <= checker && checker>0) {
					dispenseCoins.add(Currency.valueOf(availableCoins.get(i)));
					checker -= availableCoins.get(i);
					
				}
				
			}
			if(checker!=0) 
				new DispenseException("Exact change not available");
		
		//updating the vending cash on sucess
		for(Currency amount: amountEntered) {
			stock.getCash().put(amount, stock.getCash().get(amount) + 1);
		}
		//updating the amount from the vending cash post reversal of change
		for (Currency unit : dispenseCoins) {
			stock.getCash().put(unit, stock.getCash().get(unit) - 1);
		}
		return dispenseCoins;
	}
	
	@Override
	public Double updatedVendingBalance() {
		return stock.getCash().entrySet().stream()
				.mapToDouble(e -> e.getKey().getvalue() * e.getValue()).sum();
	}
	
	@Override
	public int stockCount(Product product) {
		return stock.getProducts().get(product);
	}
	
	@Override
	public void addProduct(Product product) {
		stock.addProduct(product);
	}
	
	@Override
	public void addCash(Currency feed) {
		stock.addcash(feed);
	}

	
	@Override
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	

}
