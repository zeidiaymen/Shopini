package com.example.demo.service;

import java.io.IOException;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.demo.models.*;
import com.example.demo.utils.Utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private Configuration config;
	
	public static boolean between(int variable, int minValueInclusive, int maxValueInclusive) {
		return variable >= minValueInclusive && variable <= maxValueInclusive;
	}
	


	public MailResponse sendEmail(MailRequest mailRequest) {
		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment

			Template t = config.getTemplate("email-template.ftl");
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", mailRequest.getName());
			model.put("body", mailRequest.getBody());
			model.put("bonjourOrBonsoir", Utils.GetMessageBonsoirOrBonjour());
			model.put("buttonTitle", mailRequest.getButtonTitle());
			model.put("buttonHref", mailRequest.getButtonHref());

			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			helper.setTo(mailRequest.getTo());
			helper.setText(html, true);
			helper.setSubject(mailRequest.getSubject());
			
			sender.send(message);

			response.setMessage("mail send to : "+mailRequest.getTo() );
			response.setStatus(Boolean.TRUE);

		} catch (MessagingException  e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
		catch (IOException  e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
		catch ( TemplateException e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		return response;
	}

}
