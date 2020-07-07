package com.deloitte.vendingmachine.entity;

public enum Currency {
	PENNY(1.00), NICKLE(5.00), DIME(10.00), QUARTER(25.00), DOLLAR(100.00);

	private double value;

	private Currency(double value) {
		this.value = value;
	}

	public double getvalue() {
		return value;
	}
	
	public static  Currency valueOf(double value) {
	    for (Currency e : values()) {
	        if (e.value==value) {
	            return e;
	        }
	    }
	    return null;
	}
}
