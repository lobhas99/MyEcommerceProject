package com.app.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender sendMail;

	public void sendSimpleEmail(String toEmail, String body, String subject) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("lobhas12@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		System.out.println(toEmail);
		System.out.println(body);
		System.out.println(subject);
		sendMail.send(message);

		System.out.println("Mail Sent...");
	}

	public void sendOtp(String toEmail, String otp, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("lobhas12@gmail.com");
		message.setTo(toEmail);
		message.setText(otp);
		message.setSubject(subject);

		System.out.println(toEmail);
		System.out.println(otp);
		System.out.println(subject);

		sendMail.send(message);

		System.out.println("OTP Sent...");

	}
}
