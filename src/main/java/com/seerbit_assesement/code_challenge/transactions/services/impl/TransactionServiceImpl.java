package com.seerbit_assesement.code_challenge.transactions.services.impl;

import com.seerbit_assesement.code_challenge.transactions.dto.TransactionRequest;
import com.seerbit_assesement.code_challenge.transactions.exceptions.DateTimeParseException;
import com.seerbit_assesement.code_challenge.transactions.exceptions.NotFoundException;
import com.seerbit_assesement.code_challenge.transactions.exceptions.ThirtySecondAgoException;
import com.seerbit_assesement.code_challenge.transactions.model.Transaction;
import com.seerbit_assesement.code_challenge.transactions.model.TransactionStatistics;
import com.seerbit_assesement.code_challenge.transactions.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final Deque<Transaction> transactions = new ArrayDeque<>();
    private final AtomicReference<BigDecimal> sum = new AtomicReference<>(BigDecimal.ZERO);
    private final AtomicReference<BigDecimal> max = new AtomicReference<>(BigDecimal.valueOf(Double.MIN_VALUE));
    private final AtomicReference<BigDecimal> min = new AtomicReference<>(BigDecimal.valueOf(Double.MAX_VALUE));
    private final AtomicLong count = new AtomicLong(0);
    private final Object lock = new Object();



    @Override
    public void addTransaction(TransactionRequest request) {
        ZonedDateTime transactionTimeStamp;
        transactionTimeStamp = ZonedDateTime.parse(request.getTimeStamp());
        var thirtySecondsAgo = ZonedDateTime.now(ZoneOffset.UTC).minusSeconds(30);
        BigDecimal amount = new BigDecimal(request.getAmount()).setScale(2, RoundingMode.HALF_UP);

        // Check if transaction is in the future
        if (transactionTimeStamp.toLocalDate().isAfter(ZonedDateTime.now().toLocalDate())) {
            throw new DateTimeParseException("Invalid timestamp: future date");
        }
        // Check if transaction is older than 30 seconds
        if (transactionTimeStamp.isBefore(thirtySecondsAgo)) {
            throw new ThirtySecondAgoException("The transaction time is older than 30 seconds ");
        }

            try {
                var transaction = Transaction.builder()
                        .amount(amount)
                        .timeStamp(transactionTimeStamp)
                        .build();
                synchronized (lock){
                    transactions.add(transaction);
                    sum.updateAndGet(s -> s.add(amount));
                    max.updateAndGet(m -> m.max(amount));
                    min.updateAndGet(m -> m.min(amount));
                    count.incrementAndGet();
                }
            } catch (DateTimeParseException ex) {
                throw new DateTimeParseException("Invalid timestamp: not in the correct format");
            }

    }

    @Override
    public TransactionStatistics getStatistics() {
        synchronized (lock) {
            ZonedDateTime thirtySecondsAgo = ZonedDateTime.now(ZoneOffset.UTC).minusSeconds(30);

            if (transactions.isEmpty() || transactions.peekFirst().getTimeStamp().isBefore(thirtySecondsAgo)) {
                throw new NotFoundException("No transactions within the last 30 seconds");
            }
            BigDecimal avg = sum.get().divide(BigDecimal.valueOf(count.get()), 2, RoundingMode.HALF_UP);
            return new TransactionStatistics(sum.get(), max.get(), min.get(), avg, count.get());
        }
    }

    @Override
    public void deleteTransactions() {
        synchronized (lock) {
            transactions.clear();
            sum.set(BigDecimal.ZERO);
            max.set(BigDecimal.valueOf(Double.MIN_VALUE));
            min.set(BigDecimal.valueOf(Double.MAX_VALUE));
            count.set(0);
        }
    }

    @Override
    public List<Transaction> getAllTransaction() {
        synchronized (lock) {
            if (transactions.isEmpty()) {
                throw new NotFoundException("Transaction records not found");
            }
            return new ArrayList<>(transactions);
        }
    }

}
