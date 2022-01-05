package com.testeweb.course.dto;

import java.io.Serializable;

import com.testeweb.course.domain.Categoria;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
public class CategoriaDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty(message = "Preenchimento do campo obrigatorio")
	@Length(min=5,max=80,message = "o tamanho deve ser entre 5 e 80 caractere")
	private String nome;
	
	public CategoriaDTO() {
	
	}
	
	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
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
	
	
	
}
