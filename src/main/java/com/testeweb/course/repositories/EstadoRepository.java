package com.testeweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeweb.course.domain.Estado;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
