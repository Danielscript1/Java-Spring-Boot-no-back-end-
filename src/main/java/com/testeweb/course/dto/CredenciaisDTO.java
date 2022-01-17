package com.testeweb.course.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*---processo de autenticação---
	 *    Criar classe de usuário conforme contrato do Spring Security (implements UserDetails) = UserSS ->contem os metodos de autenticação que vão trabalhar encima dece usuario
		  Criar classe de serviço conforme contrato do Spring Security (implements UserDetailsService) = UserDetailsService ->permite a busca pelo nome do usuario
		  Em SecurityConfig, sobrescrever o método: public void configure(AuthenticationManagerBuilder auth) 
		  Criar a classe CredenciaisDTO (usuário e senha) -> OK, que uma claase para receber usuario e senha 
		  Incluir as propriedades de JWT (segredo e tempo de expiração) em application.properties  
		  Criar uma classe JWTUtil (@Component) com a operação String generateToken(String username) 
		  Criar um filtro de autenticação 
		  Em SecurityConfig, registrar o filtro de autenticação
	 * 
	 * */
	private String email;
	private String senha;
	
	public CredenciaisDTO() {
		
	}

	public CredenciaisDTO(String email, String senha) {
		
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
