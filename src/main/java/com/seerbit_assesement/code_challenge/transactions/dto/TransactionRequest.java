package com.seerbit_assesement.code_challenge.transactions.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TransactionRequest {
    @NotEmpty
    private String amount;
}
