package com.deloitte.vendingmachine.service;

public interface VendingMachineFactory {

	public static VendingMachine createVendingMachine() {
        return new VendingMachineImpl();
    }
}
