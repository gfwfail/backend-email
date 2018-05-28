package com.siteminder.email.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siteminder.email.entity.Email;
import com.siteminder.email.entity.ValidResponse;
import com.siteminder.email.exception.MailServiceUnavaliableException;
import com.siteminder.email.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }


    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<ValidResponse> sendEmail(@Validated @RequestBody Email email) throws MailServiceUnavaliableException, JsonProcessingException {
        mailService.sendEmail(email);
        ValidResponse validResponse = new ValidResponse();
        validResponse.setMessage("Email has been successfully Sent.");
        validResponse.setPayload(email);

        return new ResponseEntity<ValidResponse>(validResponse, HttpStatus.OK);
    }
}

