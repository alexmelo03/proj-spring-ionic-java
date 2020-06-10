package com.devalex.spring_ionic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devalex.spring_ionic.domain.Estado;
import com.devalex.spring_ionic.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;

	public List<Estado> buscarPorTodos() {
		return repo.findAllByOrderByNome();
	}

}
