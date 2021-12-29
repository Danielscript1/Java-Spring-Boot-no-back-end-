package com.testeweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.Cidade;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
