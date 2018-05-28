/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.service;

import com.siteminder.email.entity.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;

public abstract class MailServiceProvider {

    @Autowired
    protected Environment env;
    static final Logger logger = LoggerFactory.getLogger(MailGunServiceProvider.class);
    public abstract boolean sendEmail(Email email) throws IOException;
}
