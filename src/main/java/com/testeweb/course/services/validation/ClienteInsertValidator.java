package com.testeweb.course.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.testeweb.course.domain.Cliente;
import com.testeweb.course.domain.enums.TipoCliente;
import com.testeweb.course.dto.ClienteNewDTO;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.resources.exception.FieldMessage;
import com.testeweb.course.services.validation.utils.BR;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void initialize(ClienteInsert ann) {

	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();//MINHA lista fieldMessage

		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipo().equals(TipoCliente.PessoaFisica.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF Invalido!"));
		}
		if(objDto.getTipo().equals(TipoCliente.PessoaJuridica.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ invalido!"));
		}
		
		//logica de validação de email existente
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux !=null) {
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
