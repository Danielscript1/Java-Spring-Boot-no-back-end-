package com.testeweb.course.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/*
 * classe que vai criar um email de mentira, para fazer teste aqui no meu console
 * 
 * */
public class MockEmailService extends AbstractEmailService{
	
	
	//estou criando static, para criar somente uma vez
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("simulando envio de email");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		
	}

}
