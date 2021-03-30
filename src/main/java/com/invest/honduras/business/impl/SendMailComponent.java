package com.invest.honduras.business.impl;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.invest.honduras.config.ApplicationProperties;
import com.invest.honduras.error.GlobalException;
import com.invest.honduras.util.Constant;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendMailComponent {

	@Autowired
	JavaMailSender javaMail;
	@Autowired
	TemplateEngine templateEngine;
	@Autowired
	ApplicationProperties applicationProperties;

	public boolean sendMail(Map<String, Object> mapNotify, String sendTo, String typeTemplate) {

		boolean status = true;
		MimeMessage message = javaMail.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			Context context = new Context();
			context.setVariables(mapNotify);
			String html = templateEngine.process(typeTemplate, context);
			helper.setTo(sendTo);
			helper.setText(html, true);
			helper.setSubject(mapNotify.get("subject").toString());
			helper.setFrom(applicationProperties.getMailFrom());
			helper.addInline("logo.jpg", new ClassPathResource(applicationProperties.getLogo()));
			helper.addInline("logoBid.jpg", new ClassPathResource(applicationProperties.getLogoBid()));
			javaMail.send(message);
		} catch (MessagingException e) {
			status = false;
			log.error("sendMail.MessagingException", e);

			throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR,
					Constant.MESSAGE_CERTIFICATE_NOT_DOCUMENT_EXIST); 

		} catch (Exception e) {
			status = false;
			log.error("sendMail.Exception", e);

			throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR,
					Constant.MESSAGE_CERTIFICATE_NOT_DOCUMENT_EXIST);

		}

		return status;

	}

}
