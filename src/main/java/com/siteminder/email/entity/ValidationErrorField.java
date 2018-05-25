/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.entity;

import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationErrorField {

    private String field;
    private String message;

    public ValidationErrorField(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ValidationErrorField() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
