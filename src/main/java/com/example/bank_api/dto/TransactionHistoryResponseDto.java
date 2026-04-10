package com.example.bank_api.dto;

import com.example.bank_api.model.TransferCategory;

import java.util.UUID;

public class TransactionHistoryResponseDto {
	private UUID transactionID;
	private Long from;
	private Long to;
	private int amount;
	private TransferCategory value;

	//--[getter]----------------------------------------------------------------
	public UUID getTransactionID() { return transactionID; }
	public Long getFrom() { return from; }
	public Long getTo() { return to; }
	public int getAmount() { return amount; }

	//--[setter]----------------------------------------------------------------
	public void setTransactionID(UUID transactionID) {
		this.transactionID = transactionID;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setTransferCategory(TransferCategory value) {
		this.value = value;
	}
}
