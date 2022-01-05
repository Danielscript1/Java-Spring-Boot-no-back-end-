package com.testeweb.course.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	
	/**
	 * minha classe que busca os filtros desejados para exibir
	 */
	
	private static final long serialVersionUID = 1L;
	private String Fieldnome;
	private String message;
	
	
	public FieldMessage() {
		
	}


	public FieldMessage(String fieldnome, String message) {
		
		this.Fieldnome = fieldnome;
		this.message = message;
	}


	public String getFieldnome() {
		return Fieldnome;
	}


	public void setFieldnome(String fieldnome) {
		Fieldnome = fieldnome;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
	
}
