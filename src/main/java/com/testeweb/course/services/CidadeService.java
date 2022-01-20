package com.testeweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Categoria;
import com.testeweb.course.domain.Cidade;
import com.testeweb.course.repositories.CidadeRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;
@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<Cidade> findByEstado(Long estadoId) {
		return repo.findCidades(estadoId);
	}

}
