package com.testeweb.course.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.testeweb.course.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	@Value("${default.sender}")
	private String sender;
	
	//vou instancia prepareSimpleEmail,passando obj com argumento
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		//chamar o sendEmail aqui, e passando obj como argumento
		sendEmail(sm);
	}
	
	/*Esta classe é usada para enviar o e-mail.
	 *  Aqui preparamos o objeto SimpleMailMessage que representa seu email.
	 *  método usado para enviar e-mail./*
	 */
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		//instancia minha classe com as informaçoes de envio
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail()); //pegar o email de quem , fez a solicitaçãp
		sm.setFrom(sender);//email remetente,quem esta enviando o email
		sm.setSubject("Pedido confirmado! Codigo"+obj.getId());//assunto do email
		sm.setSentDate(new Date(System.currentTimeMillis()));//data do email, tambem vai garantir que vai cirar uma data com horario do meu servidor
		sm.setText(obj.toString());//Corpo do email
		return sm;
	}
	
	
}
