package com.testeweb.course.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.testeweb.course.domain.Cliente;
import com.testeweb.course.domain.Pedido;

public interface EmailService {
	//enviar confirmação de envio de email, passando Pedido obj com argumento
	void sendOrderConfirmationEmail(Pedido obj);
	
	//prepara o email
	void sendEmail(SimpleMailMessage msg);
	
	//confirmação de recebimento de email
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	//email pensonalizado
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass); 
}
