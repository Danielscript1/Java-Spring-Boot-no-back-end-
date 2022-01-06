package com.testeweb.course.dto;

import java.io.Serializable;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Cliente;
import com.testeweb.course.services.validation.ClienteUpdate;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
@ClienteUpdate
public class ClienteDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty(message = "Preenchimento do campo obrigatorio")
	@Length(min=5,max=80,message = "o tamanho deve ser entre 5 e 80 caractere")
	private String nome;
	@NotEmpty(message = "Preenchimento do campo obrigatorio")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$") //@Email(messege="email invalido!")
	private String email;

	
	public ClienteDTO() {
	
	}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
