package com.nagaro.exception;

public class AccountStatementNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
		
	public AccountStatementNotFoundException() {
		super();
	}

	public AccountStatementNotFoundException(String message) {
		super(message);
		
	}
}
