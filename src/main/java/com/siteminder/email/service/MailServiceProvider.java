/*
 * Copyright (c) 2018.
 * @author  Lu Ye
 */

package com.siteminder.email.service;

import com.siteminder.email.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;

public abstract class MailServiceProvider {

    @Autowired
    protected Environment env;

    public abstract boolean sendEmail(Email email) throws IOException;
}
