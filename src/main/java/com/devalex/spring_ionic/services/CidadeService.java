package com.devalex.spring_ionic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devalex.spring_ionic.domain.Cidade;
import com.devalex.spring_ionic.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;

	public List<Cidade> buscarPorEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}

}
