/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.entity;

public class ValidResponse {
    private String message;
    private Object payload;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
