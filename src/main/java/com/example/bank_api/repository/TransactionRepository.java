package com.example.bank_api.repository;

import com.example.bank_api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository
		extends JpaRepository<Transaction, UUID> {
	List<Transaction>	findBySenderIdOrRecipientIdAndSuccessTrue(Long senderId, Long recipientId);
	List<Transaction>	findBySuccessFalse();
}
