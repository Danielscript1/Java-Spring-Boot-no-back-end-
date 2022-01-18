package com.testeweb.course.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.testeweb.course.services.exception.AuthorizationException;
import com.testeweb.course.services.exception.DataIntegrityViolationException;
import com.testeweb.course.services.exception.ObjectNotFoundException;

//status http
@ControllerAdvice
public class ResourceExceptionHandler {
	
	//esse metodo recebe a excecao e a requisicao
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new  StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	//esse metodo recebe a excecao da integridade de chave violada
		@ExceptionHandler(DataIntegrityViolationException.class)
		public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request){
			StandardError err = new  StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(), System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
		
		
		//esse metodo faz a exception de validação
		@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
		public ResponseEntity<StandardError> MethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException e, HttpServletRequest request){
			ValidationError err = new  ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(),"Erro de Validação!", System.currentTimeMillis());
			
			//antes de retorna o erro , tenho que percorrer a lista de erros 
			
			for (FieldError x : e.getBindingResult().getFieldErrors()) {
				err.addError(x.getField(), x.getDefaultMessage());
			}	
			
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
		}
		
		//esse metodo recebe a excecao e a requisicao, esse metodo lança a restrição de acesso negado
		@ExceptionHandler(AuthorizationException.class)
		public ResponseEntity<StandardError> Authorization(AuthorizationException e, HttpServletRequest request){
			StandardError err = new  StandardError(HttpStatus.FORBIDDEN.value(),e.getMessage(), System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
		}
		
}
