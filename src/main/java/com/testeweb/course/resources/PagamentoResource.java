package com.testeweb.course.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.domain.Pagamento;
import com.testeweb.course.services.PagamentoService;

@RestController
@RequestMapping(value="/pagamentos")
public class PagamentoResource {
	
	@Autowired
	private PagamentoService pagamentoService; 
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pagamento> buscarId(@PathVariable Long id) {
		Pagamento pagamento = pagamentoService.buscar(id);
		return ResponseEntity.ok().body(pagamento);
	}

}