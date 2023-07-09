package com.seerbit_assesement.code_challenge.transactions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatistics {
    private BigDecimal sum;
    private BigDecimal max;
    private BigDecimal min;
    private BigDecimal avg;
    private long count;
//    public BigDecimal getAvg() {
//        if (count == 0) {
//            return BigDecimal.ZERO;
//        }
//        return sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
//    }
//
//    public void update(BigDecimal amount) {
//        sum = sum.add(amount);
//        max = max.max(amount);
//        min = min.min(amount);
//        count++;
//    }

}
