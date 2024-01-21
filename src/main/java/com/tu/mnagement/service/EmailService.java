package com.tu.mnagement.service;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendEmail(List<String> to, String subject, String body) throws MessagingException;
}