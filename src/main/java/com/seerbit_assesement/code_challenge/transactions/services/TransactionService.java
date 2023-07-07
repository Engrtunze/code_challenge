package com.seerbit_assesement.code_challenge.transactions.services;

import com.seerbit_assesement.code_challenge.transactions.model.TransactionStatistics;

public interface TransactionService {
void addTransaction(String amount);
TransactionStatistics getStatistics();
void DeleteTransactions();
}
