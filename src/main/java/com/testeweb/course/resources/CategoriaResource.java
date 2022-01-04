package com.testeweb.course.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		Categoria cat = categoriaService.find(id);
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
	
	//atualizando categoria
	@PutMapping(value="/{id}")
	public ResponseEntity<Categoria> update(@RequestBody Categoria obj,@PathVariable Long id) {
		obj.setId(id);//garantiar que vem o id desejado 
		obj = categoriaService.update(obj, obj.getId());
		return ResponseEntity.noContent().build();
	}
	
	//deletar
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Categoria> deletar(@PathVariable Long id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}