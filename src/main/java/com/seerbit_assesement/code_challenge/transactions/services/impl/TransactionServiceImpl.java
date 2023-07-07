package com.seerbit_assesement.code_challenge.transactions.services.impl;

import com.seerbit_assesement.code_challenge.transactions.model.Transaction;
import com.seerbit_assesement.code_challenge.transactions.model.TransactionStatistics;
import com.seerbit_assesement.code_challenge.transactions.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final Queue<Transaction> transactions = new ConcurrentLinkedDeque<>();
    @Override
    public void addTransaction(String amount) {
        //TODO create exceptions
        ZonedDateTime currentTimeStamp = ZonedDateTime.now(ZoneOffset.UTC);
        Transaction transaction = Transaction.builder()
                        .amount(new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP))
                                .timeStamp(currentTimeStamp)
                                        .build();

        transactions.add(transaction);
    }



    @Override
    public TransactionStatistics getStatistics() {
        long lastThirtySeconds = Instant.now().minusSeconds(30).toEpochMilli();

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.valueOf(Double.MIN_VALUE);
        BigDecimal min = BigDecimal.valueOf(Double.MAX_VALUE);
        long count = 0;

        for (Transaction transaction : transactions){
            if (transaction.getTimeStamp().toInstant().toEpochMilli() > lastThirtySeconds){
                BigDecimal amount = transaction.getAmount();
                sum = sum.add(amount);
                max = max.max(amount);
                min = min.min(amount);
                count++;
            }
        }

        BigDecimal avg = count > 0 ? sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;

        return new TransactionStatistics(sum, avg, max, min, count);

    }

    @Override
    public void DeleteTransactions() {
        transactions.clear();

    }
}
