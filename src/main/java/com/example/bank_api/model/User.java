package com.example.bank_api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
	//--[fields]----------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int balance;

	@OneToMany(mappedBy = "sender")
	private List<Transaction> sentTransactions = new ArrayList<>();

	@OneToMany(mappedBy = "recipient")
	private List<Transaction> receivedTransactions = new ArrayList<>();

	//--[constructor]-----------------------------------------------------------
	protected User() {}
	public User(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}

	//--[methods]---------------------------------------------------------------
	public Long getId() { return id; }
	public String getName() { return name; }
	public Integer getBalance() { return balance; }

	/*
	validation to be done in service logic before calling this method
	- check both user balance
	- validate both against the transaction amount
	- compute the new balance for both and set them
	 */
	public void setBalance(int newBalance) { balance = newBalance; }
}
