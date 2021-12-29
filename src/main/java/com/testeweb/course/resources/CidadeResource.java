package com.testeweb.course.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Cidade;
import com.testeweb.course.services.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeResource {
	@Autowired
	private CidadeService cidadeService; 
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cidade> buscarId(@PathVariable Long id) {
		Cidade cidade = cidadeService.buscar(id);
		return ResponseEntity.ok().body(cidade);
	}
}
