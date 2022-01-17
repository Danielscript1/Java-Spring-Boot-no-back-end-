package com.testeweb.course.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	/* --autenticação
	 *   Criar classe de usuário conforme contrato do Spring Security (implements UserDetails) 
		  Criar classe de serviço conforme contrato do Spring Security (implements UserDetailsService) 
		  Em SecurityConfig, sobrescrever o método: public void configure(AuthenticationManagerBuilder auth) 
		  Criar a classe CredenciaisDTO (usuário e senha) 
		  Incluir as propriedades de JWT (segredo e tempo de expiração) em application.properties  
		  Criar uma classe JWTUtil (@Component) com a operação String generateToken(String username),para gerar meu token
		  Criar um filtro de autenticação 
		  Em SecurityConfig, registrar o filtro de autenticação  
	 * */
	
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long Expiration;
	
	//metodo para gerar o token,para enviar ao usuario
	public String generateToken(String username) {
		return Jwts.builder()//gerar o token
			   .setSubject(username) //inserir o usuario	
			   .setExpiration(new Date(System.currentTimeMillis() + Expiration ))//tempo de expiração
			   .signWith(SignatureAlgorithm.HS512,secret.getBytes()) //para dizer como vou assinar meu token, passando o meu algoritmo,palavra secreta, para embaralhar junto ao token
			   .compact();
	}
}
