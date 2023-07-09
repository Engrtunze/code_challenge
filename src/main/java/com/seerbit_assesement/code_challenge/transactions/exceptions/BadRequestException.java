package com.seerbit_assesement.code_challenge.transactions.exceptions;



public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
