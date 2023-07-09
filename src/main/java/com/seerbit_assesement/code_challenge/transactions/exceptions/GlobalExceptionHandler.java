package com.seerbit_assesement.code_challenge.transactions.exceptions;

import com.seerbit_assesement.code_challenge.transactions.dto.ErrorMessageDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DateTimeParseException.class)
    protected ResponseEntity<Object> handleDateTimeParseException(RuntimeException ex, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage(ex.getMessage());
        errorMessageDto.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorMessageDto.setCode("UNPROCESSABLE_ENTITY");
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);

    }
    @ExceptionHandler(NumberFromatException.class)
    protected ResponseEntity<Object> handleNumberFormatException(RuntimeException ex, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage(ex.getMessage());
        errorMessageDto.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorMessageDto.setCode("UNPROCESSABLE_ENTITY");
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);

    }
    @ExceptionHandler(ThirtySecondAgoException.class)
    protected ResponseEntity<Object> handleThirtySecondAgoException(RuntimeException ex, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage(ex.getMessage());
        errorMessageDto.setStatusCode(HttpStatus.NO_CONTENT.value());
        errorMessageDto.setCode("NO_CONTENT");
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.NO_CONTENT, request);

    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage(ex.getMessage());
        errorMessageDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorMessageDto.setCode("BAD_REQUEST");
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage(ex.getMessage());
        errorMessageDto.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessageDto.setCode("NOT_FOUND");
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}

