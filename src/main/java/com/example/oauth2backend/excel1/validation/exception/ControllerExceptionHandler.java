package com.example.oauth2backend.excel1.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ExcelFieldValidation.class)
    public ResponseEntity<ErrorMessage> handleExcelFieldValidationException(ExcelFieldValidation e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
