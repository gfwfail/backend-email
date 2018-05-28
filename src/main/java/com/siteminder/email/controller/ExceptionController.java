package com.siteminder.email.controller;

import com.siteminder.email.entity.ErrorResponse;
import com.siteminder.email.entity.ValidationErrorField;
import com.siteminder.email.entity.ValidationErrorResponse;
import com.siteminder.email.exception.MailServiceUnavaliableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setMessage("Invalid inputs.");
        response.setErrors(result.getFieldErrors().stream().map(fieldError -> new ValidationErrorField(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList()));

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = MailServiceUnavaliableException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage("MailService is currently not avaliable.");

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
}