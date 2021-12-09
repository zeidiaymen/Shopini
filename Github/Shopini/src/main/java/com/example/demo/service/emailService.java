package com.example.demo.service;

import java.io.File;
import java.util.Base64;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
@Service
public class emailService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach) throws MailException, MessagingException
	{
		MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    helper.setTo(to);
	    helper.setText(body);
	    if (fileToAttach.length() >10) { 
	    byte[] doc = Base64.getDecoder().decode(fileToAttach.substring(fileToAttach.indexOf(",")+1));

	    helper.addAttachment("doc.pdf", new ByteArrayResource(doc));
	    }
	    helper.setSubject("test");
	    mailSender.send(message);
	}
}
