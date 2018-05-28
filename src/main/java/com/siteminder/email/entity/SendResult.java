/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.entity;

public class SendResult {
    private String provider;
    private int code;
    private String message;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SendResult() {

    }

    public SendResult(String provider, int code) {
        this.provider = provider;
        this.code = code;
    }

    public SendResult(String provider, int code, String message) {
        this.provider = provider;
        this.code = code;
        this.message = message;
    }
}
