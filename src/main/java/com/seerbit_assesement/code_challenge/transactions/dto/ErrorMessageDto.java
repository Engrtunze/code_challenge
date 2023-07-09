package com.seerbit_assesement.code_challenge.transactions.dto;

import lombok.Data;

@Data
public class ErrorMessageDto {
    private String message;
   private int statusCode;
   private String code;
}
