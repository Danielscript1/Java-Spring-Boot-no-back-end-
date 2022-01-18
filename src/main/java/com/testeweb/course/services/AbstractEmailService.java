package com.testeweb.course.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.testeweb.course.domain.Cliente;
import com.testeweb.course.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	@Value("${default.sender}")
	private String sender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender javaMailSender;
	
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
	/* Em AbstractEmailService, incluir o seguinte método,
	 * que será responsável por retornar o HTML preenchido com 
	  os dados de um pedido, a partir do template Thymeleaf: 
	 * 
	 * 
	 * */
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();//para acessar meu template
		context.setVariable("pedido", obj);//passando os dados correspondente , igual esta no template
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	//Em AbstractEmailService, implementar o novo contrato: 
	//vou instancia prepareSimpleEmail,passando obj com argumento
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromPedido(obj);
			//chamar o sendEmail aqui, e passando obj como argumento
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
			
		}
		
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		//instancia para criar msg
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		//instancia para atribuir valores
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! " + obj.getId());
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
		
	}
	
	//enviar nova senha por email
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
}
