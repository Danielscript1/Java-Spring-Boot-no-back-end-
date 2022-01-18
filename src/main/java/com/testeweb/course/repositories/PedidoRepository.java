package com.testeweb.course.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.testeweb.course.domain.Cliente;
import com.testeweb.course.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Transactional(readOnly=true)//cliente so recuperar, os pedidos associados a ele
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
	
}
