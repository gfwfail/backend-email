package com.siteminder.email.service;

import com.siteminder.email.entity.Email;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class MailGunServiceProvider extends MailServiceProvider {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public boolean sendEmail(Email email) throws IOException {
        logger.info("mailgun");
        RequestBody formBody = buildForm(email);
        Request request = new Request.Builder()
                .url(env.getProperty("mailgun.url"))
                .post(formBody)
                .addHeader("Authorization", Credentials.basic("api", env.getProperty("mailgun.key")))
                .build();
        Response response = client.newCall(request).execute();

        return response.isSuccessful();
    }

    /*
    Build form payload for mailgun.
     */
    private FormBody buildForm(Email email) {
        System.out.println(email.getToRecipients().toString());
        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("from", email.getSender().toString())
                .add("subject", email.getSubject()).add("text", email.getContent());
        email.getToRecipients().forEach(to -> formBodyBuilder.add("to", to.toString()));
        email.getCcRecipients().forEach(cc -> formBodyBuilder.add("cc", cc.toString()));
        email.getBccRecipients().forEach(bcc -> formBodyBuilder.add("bcc", bcc.toString()));

        return formBodyBuilder.build();
    }


}