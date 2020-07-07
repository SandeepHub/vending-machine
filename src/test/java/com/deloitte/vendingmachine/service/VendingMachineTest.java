package com.deloitte.vendingmachine.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.deloitte.vendingmachine.exceptions.DispenseException;
import com.deloitte.vendingmachine.entity.Currency;
import com.deloitte.vendingmachine.entity.Dispenser;
import com.deloitte.vendingmachine.entity.Product;
import com.deloitte.vendingmachine.entity.Stock;

public class VendingMachineTest {

	private static VendingMachine vendingMachine;

	@BeforeClass
	public static void setUp() {
		vendingMachine = VendingMachineFactory.createVendingMachine();
		Stock stock = new Stock();

		Map<Product, Integer> initProducts = new HashMap<>();
		initProducts.put(new Product("Cold Drink", 25.00), 4);
		initProducts.put(new Product("Chocolate", 50.00), 5);
		initProducts.put(new Product("Cookies", 10.00), 7);
		initProducts.put(new Product("Snacks", 5.00), 4);
		stock.setProducts(initProducts);

		Map<Currency, Integer> initCash = new HashMap<>();
		initCash.put(Currency.DIME, 5);
		initCash.put(Currency.DOLLAR, 2);
		initCash.put(Currency.NICKLE, 7);
		initCash.put(Currency.QUARTER, 4);
		initCash.put(Currency.PENNY, 24);
		stock.setCash(initCash);
		vendingMachine.setStock(stock);
	}

	@AfterClass
	public static void tearDown() {
		vendingMachine = null;
	}

	@Test
	public void testGetProducts() {

		Set<Product> products = vendingMachine.getAllProducts();
		assertNotNull(products);
		assertTrue(products.stream().anyMatch(p -> "Chocolate".equalsIgnoreCase(p.getName())));
	}

	@Test(expected = DispenseException.class)
	public void testunavailableProduct() {
		Product nonExistingProduct = new Product("Bread", 5.00);
		vendingMachine.selectProduct(nonExistingProduct);
	}

	@Test
	public void testAvailableProduct() {
		Product existingProduct = new Product("Snacks", 5.00);
		assertEquals("SUCCESS", vendingMachine.selectProduct(existingProduct));
	}

	@Test(expected = DispenseException.class)
	public void testAvailableProductAndInsuffientAmount() {
		Product existingProduct = new Product("Chocolate", 50.00);
		List<Currency> coins = new ArrayList<>();
		coins.add(Currency.DIME);
		vendingMachine.selectProduct(existingProduct);
		vendingMachine.insertDenomination(coins);

	}

	@Test
	public void testAvailableProductAndSuffientAmount() {
		Product existingProduct = new Product("Snacks", 5.00);
		List<Currency> coins = new ArrayList<>();
		coins.add(Currency.DIME);
		// select Product
		vendingMachine.selectProduct(existingProduct);
		// Enter Amount
		vendingMachine.insertDenomination(coins);
		// Dispense if valid
		Dispenser response = vendingMachine.dispenseProductAndBalance();
		assertEquals(Double.valueOf(response.getCurrencies().get(0).getvalue()), Double.valueOf(5));

	}

	@Test
	public void testVendingBalanceAfterSale() {
		Product existingProduct = new Product("Snacks", 5.00);
		List<Currency> coins = new ArrayList<>();
		coins.add(Currency.DIME);
		vendingMachine.selectProduct(existingProduct);
		vendingMachine.insertDenomination(coins);
		vendingMachine.dispenseProductAndBalance();

		assertEquals(vendingMachine.updatedVendingBalance(), Double.valueOf(444));

	}

	@Test
	public void testProductStockBalanceAfterSale() {
		Product existingProduct = new Product("Cold Drink", 25.00);
		List<Currency> coins = new ArrayList<>();
		coins.add(Currency.DOLLAR);
		int count = vendingMachine.stockCount(existingProduct);
		// before dispense
		vendingMachine.selectProduct(existingProduct);
		vendingMachine.insertDenomination(coins);
		vendingMachine.dispenseProductAndBalance();
		// post dispense
		assertEquals(vendingMachine.stockCount(existingProduct), count - 1);

	}
}
