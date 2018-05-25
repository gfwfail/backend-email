/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.entity;

import java.util.List;

public class ValidationErrorResponse {

    private String message;
    private List<ValidationErrorField> errors;

    public ValidationErrorResponse() {
    }

    public List<ValidationErrorField> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationErrorField> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
