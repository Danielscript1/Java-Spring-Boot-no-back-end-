package com.testeweb.course.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Pedido;
import com.testeweb.course.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService; 
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> buscarId(@PathVariable Long id) {
		Pedido pedido = pedidoService.buscar(id);
		return ResponseEntity.ok().body(pedido);
	}

}