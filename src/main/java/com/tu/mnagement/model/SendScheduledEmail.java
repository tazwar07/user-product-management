package com.tu.mnagement.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendScheduledEmail {

	@Autowired
	EmailService emailService;

	private volatile boolean emailSent = false;

	public String sendScheduledEmail(List<String> toEmail, String subjects, String body,
			LocalDateTime scheduledDateTime) {
		try {

			long initialDelay = calculateInitialDelay(scheduledDateTime);
			try {
				TimeUnit.SECONDS.sleep(initialDelay);
				if (!emailSent) {

					Runnable runnable = () -> {

						emailService.sendEmail(toEmail, subjects, body);
						emailSent = true;

					};

					Thread thread = new Thread(runnable);
					thread.start();

				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}

			return "Email scheduled successfully!";
		} catch (Exception ex) {
			return "Failed to schedule email: " + ex.getMessage();
		}
	}

	private long calculateInitialDelay(LocalDateTime scheduledDateTime) {
		LocalDateTime now = LocalDateTime.now();
		return now.until(scheduledDateTime, ChronoUnit.SECONDS);
	}
}