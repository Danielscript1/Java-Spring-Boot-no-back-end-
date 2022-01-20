package com.testeweb.course.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.domain.Estado;
import com.testeweb.course.dto.EstadoDTO;
import com.testeweb.course.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	@Autowired
	private EstadoService estadoService; 
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Estado> buscarId(@PathVariable Long id) {
		Estado estado = estadoService.buscar(id);
		return ResponseEntity.ok().body(estado);
	}
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = estadoService.findAll();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
}
