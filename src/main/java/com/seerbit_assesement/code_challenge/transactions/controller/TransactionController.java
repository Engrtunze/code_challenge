package com.seerbit_assesement.code_challenge.transactions.controller;

import com.seerbit_assesement.code_challenge.transactions.model.TransactionStatistics;
import com.seerbit_assesement.code_challenge.transactions.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody String amount){
        transactionService.addTransaction(amount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<TransactionStatistics> getStatistics(){
        return ResponseEntity.ok(transactionService.getStatistics());
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction(){
        transactionService.DeleteTransactions();
        return ResponseEntity.noContent().build();
    }
}
