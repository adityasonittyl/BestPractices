package com.zensar.payroll.exceptions;

public class PayRollServicesNotFoundException extends Exception {

	public PayRollServicesNotFoundException() {
		this("Sorry service is unavailable currently");
	}

	public PayRollServicesNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PayRollServicesNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PayRollServicesNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PayRollServicesNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
