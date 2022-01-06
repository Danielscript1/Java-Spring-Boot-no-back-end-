package com.testeweb.course.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.testeweb.course.domain.Cliente;
import com.testeweb.course.dto.ClienteDTO;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.resources.exception.FieldMessage;


public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private HttpServletRequest request; //esse metodo pegar os paramentros passado na url
	@Override
	public void initialize(ClienteUpdate ann) {

	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		
		//pegar os dados da url ,desejados
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		//Buscar pelo campo, desejado
		Integer uriId = Integer.parseInt( map.get("id")); //faz a conversao de string para inteiro
		
		List<FieldMessage> list = new ArrayList<>();//MINHA lista fieldMessage
		//logica de validação de email existente
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux !=null && !aux.getId().equals(uriId)) {//verificar se ID, E DIFENTE DO ID QUE QUERO ATUALIZAR
			list.add(new FieldMessage("Email", "email já existente"));
		}
		

		for (FieldMessage e : list) {//APROVEITEI DA MINHA CLASSE DE ERRO FIELDMESSAGE
			context.disableDefaultConstraintViolation();//esse codigos e proprio do framework, não devo me preocupar, apenas estou lançando os erros para os campos 
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldnome())
					.addConstraintViolation();
		}

		return list.isEmpty();//se a lista estiver vazia então o teste passou,é verdadeiro

	}

}
