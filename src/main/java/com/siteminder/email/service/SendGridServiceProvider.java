/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siteminder.email.entity.Email;
import com.siteminder.email.entity.EmailContact;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;


@Service
public class SendGridServiceProvider extends MailServiceProvider {
    static final Logger logger = LoggerFactory.getLogger(SendGridServiceProvider.class);
    private final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    public boolean sendEmail(Email email) throws IOException {
        RequestBody jsonBody = buildJson(email);

        Request request;

        request = new Request.Builder().url(env.getProperty("sendgrid.url")).post(jsonBody).addHeader
                ("Authorization", "Bearer " + env.getProperty("sendgrid.key")).build();
        Response response = client.newCall(request).execute();
        logger.info(response.body().string());
        return response.isSuccessful();
    }

    /*
       Build json payload for sendgrid.
   */
    private RequestBody buildJson(Email email) throws JsonProcessingException {
        String json = "{\"personalizations\": [{\"to\": " + buildEmailContactCollection(email.getToRecipients()) +
                (email.getCcRecipients().size() > 0 ? ",\"cc\":" + buildEmailContactCollection(email.getCcRecipients
                        ()) : "") + (email.getBccRecipients().size() > 0 ? ",\"bcc\":" + buildEmailContactCollection
                (email.getBccRecipients()) : "") + ",}]," + "\"from\":" + buildEmailContact(email.getSender()) + "" +
                "," + "\"subject\": " + "" + "\"" + email.getSubject() + "\"," + "\"content\":" + " " + "[{\"type\": " +
                "" + "" + "\"text/plain\", " + "\"value\": \"" + email.getContent() + "\"}]}";
        return RequestBody.create(JSON, json);
    }

    private String buildEmailContactCollection(HashSet<EmailContact> emailContacts) {
        return "[" + String.join(",", emailContacts.stream().map(this::buildEmailContact).collect(Collectors.toList()
        )) + "]";
    }

    private String buildEmailContact(EmailContact emailContact) {
        if (emailContact.getName() == null) {
            return "{\"email\": \"" + emailContact.getEmail() + "\"}";
        }
        return "{  \"name\":\"" + emailContact.getName() + "\",\"email\": \"" + emailContact.getEmail() + "\"}";
    }


}