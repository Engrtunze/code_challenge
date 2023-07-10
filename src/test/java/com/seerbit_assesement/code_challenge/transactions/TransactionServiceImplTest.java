package com.seerbit_assesement.code_challenge.transactions;

import com.seerbit_assesement.code_challenge.transactions.dto.TransactionRequest;
import com.seerbit_assesement.code_challenge.transactions.exceptions.NotFoundException;
import com.seerbit_assesement.code_challenge.transactions.exceptions.ThirtySecondAgoException;
import com.seerbit_assesement.code_challenge.transactions.model.TransactionStatistics;
import com.seerbit_assesement.code_challenge.transactions.services.TransactionService;
import com.seerbit_assesement.code_challenge.transactions.services.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionServiceImplTest {
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl();
    }

    @Test
    void addTransaction_ValidTransaction_AddsTransactionSuccessfully() {
        TransactionRequest request = new TransactionRequest();
        request.setAmount("100.00");
        request.setTimeStamp(ZonedDateTime.now().toString());

        assertDoesNotThrow(() -> transactionService.addTransaction(request));
    }


    @Test
    void addTransaction_OlderTransaction_ThrowsThirtySecondAgoException() {
        TransactionRequest request = new TransactionRequest();
        request.setAmount("100.00");
        request.setTimeStamp(ZonedDateTime.now().minusSeconds(31).toString());

        assertThrows(ThirtySecondAgoException.class, () -> transactionService.addTransaction(request));
    }

    @Test
    void getStatistics_NoTransactionsWithinLast30Seconds_ThrowsNotFoundException() {
        // Create a mock of the TransactionService with an empty transactions deque
        TransactionService transactionService = new TransactionServiceImpl();

        // Assert that NotFoundException is thrown
        assertThrows(NotFoundException.class, transactionService::getStatistics);
    }

    @Test
    void getStatistics_WithTransactions_ReturnsCorrectStatistics() {
        // Add some test transactions
        TransactionRequest request1 = new TransactionRequest();
        request1.setAmount("100.00");
        request1.setTimeStamp(ZonedDateTime.now().toString());
        transactionService.addTransaction(request1);

        TransactionRequest request2 = new TransactionRequest();
        request2.setAmount("200.00");
        request2.setTimeStamp(ZonedDateTime.now().toString());
        transactionService.addTransaction(request2);

        // Calculate expected statistics manually
        BigDecimal sum = new BigDecimal("300.00");
        BigDecimal max = new BigDecimal("200.00");
        BigDecimal min = new BigDecimal("100.00");
        BigDecimal avg = new BigDecimal("150.00");
        int count = 2;
        TransactionStatistics expectedStatistics = new TransactionStatistics(sum, max, min, avg, count);

        TransactionStatistics actualStatistics = transactionService.getStatistics();

        assertEquals(expectedStatistics, actualStatistics);
    }

}
