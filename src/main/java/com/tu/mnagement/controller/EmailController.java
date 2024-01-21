package com.tu.mnagement.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tu.mnagement.model.MultipleEmailRequest;
import com.tu.mnagement.service.EmailService;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

//    @PostMapping("/send")
//    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
//        try {
//            emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
//            return ResponseEntity.ok("Email sent successfully");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Failed to send email");
//        }
//    }

    @PostMapping("/sendMultiple")
    public ResponseEntity<String> sendMultipleEmails(@RequestBody MultipleEmailRequest multipleEmailRequest) {
        try {
            emailService.sendEmail(multipleEmailRequest.getTo(), multipleEmailRequest.getSubject(), multipleEmailRequest.getBody());
            return ResponseEntity.ok("Emails sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to send emails");
        }
    }
}