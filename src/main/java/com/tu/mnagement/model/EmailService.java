package com.tu.mnagement.model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	

	public String sendEmail(List<String> toEmail, String subjects, String body) {
	       
		for(String email : toEmail) {
			SimpleMailMessage message = new SimpleMailMessage();
		    message.setTo(email);
		    message.setSubject(subjects);
		    message.setText(body);

		    javaMailSender.send(message);
		}
	    return "Email sent successfully!";
	}
	
}