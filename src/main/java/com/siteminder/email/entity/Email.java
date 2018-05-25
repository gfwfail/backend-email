/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

public class Email {
    @NotNull
    @Valid
    private EmailContact sender;

    @NotEmpty
    @Valid
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private HashSet<EmailContact> toRecipients;

    @Valid
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private HashSet<EmailContact> ccRecipients;

    @Valid
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private HashSet<EmailContact> bccRecipients;

    @NotEmpty
    @Length(min = 1, max = 1024)
    private String subject;

    @NotEmpty
    private String content;

    public Email() {
        toRecipients = new HashSet<EmailContact>();
        ccRecipients = new HashSet<EmailContact>();
        bccRecipients = new HashSet<EmailContact>();
    }

    public EmailContact getSender() {
        return sender;
    }

    public void setSender(EmailContact sender) {
        this.sender = sender;
    }

    public HashSet<EmailContact> getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(HashSet<EmailContact> toRecipients) {
        this.toRecipients = toRecipients;
    }

    public HashSet<EmailContact> getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(HashSet<EmailContact> ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    public HashSet<EmailContact> getBccRecipients() {
        return bccRecipients;
    }

    public void setBccRecipients(HashSet<EmailContact> bccRecipients) {
        this.bccRecipients = bccRecipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Email{" + "sender=" + sender + ", toRecipients=" + toRecipients + ", ccRecipients=" + ccRecipients + ", bccRecipients=" + bccRecipients + ", subject='" + subject + '\'' + ", content='" + content + '\'' + '}';
    }

    public void setContent(String content) {
        this.content = content;
    }
}