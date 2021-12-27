package com.testeweb.course.resources;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}