package com.testeweb.course.repositories;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
	
}
