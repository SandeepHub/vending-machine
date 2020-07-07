package com.deloitte.vendingMachine.service;

public interface VendingMachineFactory {

	public static VendingMachine createVendingMachine() {
        return new VendingMachineImpl();
    }
}
