package com.deloitte.vendingMachine.exceptions;

public class DispenseException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;
	   
	    public DispenseException(String string) {
	        this.message = string;
	    }
	   
	    @Override
	    public String getMessage(){
	        return message;
	    }
}
