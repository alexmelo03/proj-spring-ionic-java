package com.devalex.spring_ionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.devalex.spring_ionic.domain.Categoria;
import com.devalex.spring_ionic.dto.CategoriaDTO;
import com.devalex.spring_ionic.repositories.CategoriaRepository;
import com.devalex.spring_ionic.services.exceptions.DataIntegrityException;
import com.devalex.spring_ionic.services.exceptions.ObjectNotFoundException;

@Service 
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
		
	public Categoria buscarPorId(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
	public Categoria inserir(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria atualizar(Categoria obj) {
		Categoria newObj = buscarPorId(obj.getId());
		atualizarDados(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		buscarPorId(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> buscarPorTodos() {
		return repo.findAll();
	}
	
	public Page<Categoria> buscaPorPagina(Integer pagina, Integer linhasPorPagina, String direcao, String ordemPor) {
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordemPor);
		return repo.findAll(pageRequest);
	}
	
	public Categoria apartirDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	private void atualizarDados(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
	

}
