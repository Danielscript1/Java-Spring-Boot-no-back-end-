package com.testeweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	
}
