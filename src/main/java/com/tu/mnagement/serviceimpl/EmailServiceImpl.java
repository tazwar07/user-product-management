package com.tu.mnagement.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tu.mnagement.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(List<String> to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String[] toAddresses = to.toArray(new String[0]);

        helper.setTo(toAddresses);
        helper.setSubject(subject);
        helper.setText(body, true);

        javaMailSender.send(message);
    }
}

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import com.tu.management.service.EmailService;
//import com.tu.management.util.BaseResponse;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.util.List;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//
//    private final JavaMailSender javaMailSender;
//    
//    @Autowired
//    EmailService emailService;
//    @Autowired
//    public EmailServiceImpl(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    @Override
//    public void sendEmail(List<String> to, String subject, String body) throws MessagingException {
//    	BaseResponse response = new BaseResponse();
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        String[] toAddresses = to.toArray(new String[0]);
//
//        helper.setTo(toAddresses);
//        helper.setSubject(subject);
//        helper.setText(body, true);
//        
//
//
//
//        javaMailSender.send(message);
//    }
//}