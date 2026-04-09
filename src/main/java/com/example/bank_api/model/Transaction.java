package com.example.bank_api.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Transaction {
	//--[fields]----------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID transactionID;

	@ManyToOne
	@JoinColumn(name = "sender_id")//refer to the field marked @Id in class User
	private User sender;

	@ManyToOne
	@JoinColumn(name = "recipient_id")//refer to the field marked @Id in class User
	private User recipient;

	@Enumerated(EnumType.STRING)
	private TransferCategory type;

	private int amount;

	private boolean success;

	//--[constructor]-----------------------------------------------------------
	protected Transaction() {}
	public Transaction(User sender,
					   User recipient,
					   int amount, boolean success) {
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
		this.success = success;
	}

	//--[methods]---------------------------------------------------------------
	public UUID getTransactionID() { return transactionID; }
	public User getSender() { return sender; }
	public User getRecipient() { return  recipient; }
	public TransferCategory getType() { return type; }
	public int getAmount() { return amount; }
	public boolean isSuccess() { return success; }
}