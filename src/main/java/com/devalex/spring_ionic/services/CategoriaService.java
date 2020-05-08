package com.devalex.spring_ionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devalex.spring_ionic.domain.Categoria;
import com.devalex.spring_ionic.repositories.CategoriaRepository;

@Service 
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscarPorId(Integer id){
		Optional<Categoria> obj =  repo.findById(id);
		return obj.orElse(null);
	}
	
	

}
