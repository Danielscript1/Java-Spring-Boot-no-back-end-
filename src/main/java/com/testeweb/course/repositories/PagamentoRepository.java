package com.testeweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Long>{
	
}
