package com.example.bank_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;


public class TransactionRequestDto {
	@NotNull
	private Long senderId;

	@NotNull
	private Long recipientId;

	@Min(1)
	private int amount;

	public Long getSenderId() { return senderId; }
	public void setSenderId(Long senderId) { this.senderId = senderId; }

	public Long getRecipientId() { return recipientId; }
	public void setRecipientId(Long recipientId) { this.recipientId = recipientId; }

	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount; }
}
