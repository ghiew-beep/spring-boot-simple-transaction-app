package com.example.bank_api.controller;

import com.example.bank_api.dto.TransactionHistoryResponseDto;
import com.example.bank_api.dto.TransactionRequestDto;
import com.example.bank_api.model.User;
import com.example.bank_api.dto.UserDto;
import com.example.bank_api.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {
	private final TransactionService service;

	public ServiceController(TransactionService service) {
		this.service = service;
	}

	@PostMapping("/users")
	public User addUser(@RequestBody UserDto userDto) {
		return service.addUser(userDto.getName(), userDto.getBalance());
	}

	@GetMapping("/users/{id}/balance")
	public int getBalance(@PathVariable Long id) {
		return service.getUserBalance(id);
	}

	@PostMapping("/transactions")
	public void performTransaction(@RequestBody TransactionRequestDto request) {
		service.performTransaction(
				request.getSenderId(),
				request.getRecipientId(),
				request.getAmount()
		);
	}

	@GetMapping("/transactions")
	public List<TransactionHistoryResponseDto>
	getTransactionHistory(@RequestParam Long userId) {
		return service.getUserHistory(userId);
	}

	@GetMapping("/admin/transactions/failed")
	public List<TransactionHistoryResponseDto>
	getFailedTransactionRecord() {
		return service.getFailedTransaction();
	}
}
