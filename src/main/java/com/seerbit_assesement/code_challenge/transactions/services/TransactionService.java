package com.seerbit_assesement.code_challenge.transactions.services;

import com.seerbit_assesement.code_challenge.transactions.dto.TransactionRequest;
import com.seerbit_assesement.code_challenge.transactions.model.Transaction;
import com.seerbit_assesement.code_challenge.transactions.model.TransactionStatistics;

import java.util.List;

public interface TransactionService {
void addTransaction(TransactionRequest request);
TransactionStatistics getStatistics();
void deleteTransactions();
List<Transaction> getAllTransaction();
}
