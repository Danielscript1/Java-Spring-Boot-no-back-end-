package com.testeweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.ItemPedido;
import com.testeweb.course.domain.pk.ItemPedidoPK;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,ItemPedidoPK>{
	
}
