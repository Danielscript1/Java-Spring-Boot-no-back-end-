package com.testeweb.course.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService{
	@Autowired
	private MailSender mailSender;
	
	//estou criando static, para criar somente uma vez
		private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("simulando envio de email");
		mailSender.send(msg);// para enviar adicione o seguinte estrução
		LOG.info("Email enviado");
		
	}

}
