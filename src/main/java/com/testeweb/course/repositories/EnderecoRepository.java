package com.testeweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.Endereco;
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	
}
