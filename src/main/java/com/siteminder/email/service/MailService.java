/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.service;

import com.siteminder.email.entity.Email;
import com.siteminder.email.exception.MailServiceUnavaliableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private final List<MailServiceProvider> providers;

    @Autowired
    public MailService(List<MailServiceProvider> providers) {
        this.providers = providers;
    }


    public void sendEmail(Email email) throws MailServiceUnavaliableException {
        if (providers.size() < 1) {
            logger.error("No MailService Provider defined. ");
            throw new MailServiceUnavaliableException();
        }

        try {

            for (MailServiceProvider mailServiceProvider : providers) {
                logger.info("Sending email --- " + mailServiceProvider.getClass() + " --- " + email.getSubject());

                if (mailServiceProvider.sendEmail(email)) return;
            }

        } catch (Exception e) {
            logger.error("MailServiceProvider Failed. ", e);
        }

        throw new MailServiceUnavaliableException();
    }

}
