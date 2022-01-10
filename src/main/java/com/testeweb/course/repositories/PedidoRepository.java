package com.testeweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
}
