package com.testeweb.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.testeweb.course.services.EmailService;
import com.testeweb.course.services.MockEmailService;
import com.testeweb.course.services.SmtpEmailService;

@Configuration
public class TestConfig {
	
	/*Para que serve o @bean?
	 Um bean é um objeto que é instanciado, montado e gerenciado pelo Spring IoC container,
	 Assim como pode usar o @Bean em um método,
	 e tornar a instância retornada pelo método como um objeto gerenciado pelo Spring
	 (seja de uma classe própria ou de terceiros)
	 * 
	 * 
	 * */
//	
//	@Bean
//	public EmailService emailService() {
//		return new MockEmailService();
//	}
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
