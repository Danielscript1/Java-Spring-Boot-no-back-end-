package com.testeweb.course.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//receber os paramentros de erro da classe FieldMessage
	private List<FieldMessage> errors = new ArrayList<>();
	
	//constructor vai bsucar os paramentros herdados da StandardErros que foi minha classe pensonalizada que criei
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}
	//retrona os erros para json
	public List<FieldMessage> getErrors() {
		return errors;
	}
	//adicionando na lista os erros recebidos no paramentro e depois istancio para classe FielMessage
	public void addError(String fielName, String messagem ) {
		errors.add(new FieldMessage(fielName,messagem));
	}

	
	
	
}
