package com.testeweb.course.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService; 
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarId(@PathVariable Long id) {
		Categoria cat = categoriaService.buscar(id);
		return ResponseEntity.ok().body(cat);
	}
	
	
	//insert processo de execução
	@PostMapping
	public ResponseEntity<Categoria> insert(@RequestBody Categoria obj){ //anotacao REquestBody , faz que o json seja convertido para objeto java
		 obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(obj.getId()).toUri(); //estou pegando o caminho na url e construindo um novo junto com id que vai ser criado
		return ResponseEntity.created(uri).build();
	}

}