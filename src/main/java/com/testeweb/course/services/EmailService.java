package com.testeweb.course.services;

import org.springframework.mail.SimpleMailMessage;

import com.testeweb.course.domain.Pedido;

public interface EmailService {
	//enviar confirmação de envio de email, passando Pedido obj com argumento
	void sendOrderConfirmationEmail(Pedido obj);
	
	//prepara o email
	void sendEmail(SimpleMailMessage msg);
}
