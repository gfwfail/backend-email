/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.service;

import com.siteminder.email.entity.Email;
import com.siteminder.email.entity.EmailContact;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class SendGridServiceProvider extends MailServiceProvider {
    private final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    static final Logger logger = LoggerFactory.getLogger(MailGunServiceProvider.class);

    @Override
    public boolean sendEmail(Email email) throws IOException {
        logger.info("sendgrid");
        RequestBody jsonBody = buildJson(email);
        Request request = new Request.Builder().url(env.getProperty("sendgrid.url")).post(jsonBody).addHeader
                ("Authorization", "Bearer " + env.getProperty("sendgrid.key")).build();
        Response response = client.newCall(request).execute();

        return response.isSuccessful();
    }

    /*
       Build json payload for sendgrid.
   */
    private RequestBody buildJson(Email email) {
        JSONObject jsonObject = new JSONObject();
        JSONObject personalizations = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("type", "text/plain");
        content.put("value", email.getContent());
        jsonObject.put("content", new JSONObject[]{content});
        jsonObject.put("personalizations", new JSONObject[]{personalizations});
        jsonObject.put("subject", email.getSubject());
        jsonObject.put("from", buildEmailContact(email.getSender()));

        if (email.getToRecipients().size() > 0) {
            personalizations.put("to", buildEmailContactCollection(email.getToRecipients()));
        }

        if (email.getCcRecipients().size() > 0) {
            personalizations.put("cc", buildEmailContactCollection(email.getToRecipients()));
        }

        if (email.getBccRecipients().size() > 0) {
            personalizations.put("bcc", buildEmailContactCollection(email.getToRecipients()));
        }

        return RequestBody.create(JSON_MEDIA_TYPE, jsonObject.toString());
    }

    private JSONArray buildEmailContactCollection(HashSet<EmailContact> emailContacts) {
        return new JSONArray(emailContacts.stream().map(this::buildEmailContact).collect(Collectors.toList()));
    }

    private JSONObject buildEmailContact(EmailContact emailContact) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", emailContact.getEmail());

        if (emailContact.getName() == null) {
            jsonObject.put("name", emailContact.getName());

        }

        return jsonObject;
    }
}
