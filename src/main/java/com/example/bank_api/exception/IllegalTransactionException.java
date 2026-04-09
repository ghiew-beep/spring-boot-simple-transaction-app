package com.example.bank_api.exception;

public class IllegalTransactionException extends RuntimeException {
	public IllegalTransactionException(String message) {
		super(message);
	}
}
