package com.example.bank_api.service;

import com.example.bank_api.exception.IllegalTransactionException;
import com.example.bank_api.exception.UserNotFoundException;
import com.example.bank_api.model.Transaction;
import com.example.bank_api.model.User;
import com.example.bank_api.repository.TransactionRepository;
import com.example.bank_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
	//--[fields]----------------------------------------------------------------
	private final UserRepository userRepo;
	private final TransactionRepository txRepository;
	private static final int MIN_TRANSFER_AMOUNT = 10;
	private static final int MAX_TRANSFER_AMOUNT = 100000;

	//--[dependency injection]--------------------------------------------------
	public TransactionService(UserRepository userRepo,
							  TransactionRepository txRepo) {
		this.userRepo = userRepo;
		this.txRepository = txRepo;
	}

	//--[method]----------------------------------------------------------------
	public User addUser(String userName, int initialBalance) {
		if (initialBalance < 0) {
			throw new IllegalArgumentException(
					"Initial balance cannot be negative");
		}
		User newUser = new User(userName, initialBalance);

		return userRepo.save(newUser);
	}

	public int getUserBalance(Long id) {
		User targetUser = userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return targetUser.getBalance();
	}

	public void
	performTransaction(Long senderID, Long recipientID, int transferAmount)
			throws UserNotFoundException, IllegalTransactionException {
		User sender = userRepo.findById(senderID)
				.orElseThrow(() -> new UserNotFoundException("Sender not found"));
		User recipient = userRepo.findById(recipientID)
				.orElseThrow(() -> new UserNotFoundException("Recipient not found"));

		if (transferAmount < MIN_TRANSFER_AMOUNT) {
			saveFailedTransaction(sender, recipient, transferAmount);
			throw new IllegalArgumentException(
					"Transaction unsuccessful: Transfer amount must be $10 and above");
		} else if (transferAmount > MAX_TRANSFER_AMOUNT) {
			saveFailedTransaction(sender, recipient, transferAmount);
			throw new IllegalTransactionException((
					"Transaction unsuccessful: Transfer amount exceeded $100000"));
		}
		if (transferAmount > sender.getBalance()) {
			saveFailedTransaction(sender, recipient, transferAmount);
			throw new IllegalTransactionException(
					"Transaction unsuccessful: Sender balance insufficient!");
		}
		sender.setBalance(sender.getBalance() - transferAmount);
		recipient.setBalance(recipient.getBalance() + transferAmount);

		userRepo.save(sender);
		userRepo.save(recipient);

		Transaction newRecord = new Transaction(sender, recipient,
				transferAmount, true);
		txRepository.save(newRecord);
	}

	private void saveFailedTransaction(User sender, User recipient,
									   int transferAmount) {
		Transaction newRecord = new Transaction(
				sender,
				recipient,
				transferAmount,
				false);
		txRepository.save(newRecord);
	}

	public List<Transaction> getUserHistory(Long userId)
			throws UserNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		return txRepository.findBySenderIDOrRecipientId(userId, userId);
	}

	public List<Transaction>	getFailedTransaction() {
		return txRepository.findBySuccessFalse();
	}
}
