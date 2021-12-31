package com.testeweb.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Pagamento;
import com.testeweb.course.repositories.PagamentoRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	//buscar categoria
	
	public Pagamento buscar(Long id) {
	Optional<Pagamento> pagamento = pagamentoRepository.findById(id); //pode haver ou não um objeto com id correpodente
	
	/*Checklist de tratamento de exceção de id inválido:
	o Criar ObjectNotFountException
	o Criar StandardError
	o Criar ResourceExceptionHandler 
	 * */
	
	return pagamento.orElseThrow(() -> new ObjectNotFoundException( //lançar minha exception pensonalizada
			 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pagamento.class.getName()));
	}
}
