package com.seerbit_assesement.code_challenge.transactions.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    private BigDecimal amount;
    private ZonedDateTime timeStamp;
}
