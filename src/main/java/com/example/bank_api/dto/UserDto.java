package com.example.bank_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
	@NotBlank
	private String name;

	@Min(1)
	private int balance;

	public String getName() { return name; }
	public int getBalance() { return balance; }

	public void setName(String name) { this.name = name; }
	public void setBalance(int balance) { this.balance = balance; }
}
