package com.devalex.spring_ionic.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devalex.spring_ionic.domain.Cidade;
import com.devalex.spring_ionic.domain.Estado;
import com.devalex.spring_ionic.dto.CidadeDTO;
import com.devalex.spring_ionic.dto.EstadoDTO;
import com.devalex.spring_ionic.services.CidadeService;
import com.devalex.spring_ionic.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService servico;

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> buscarPorTodos() {
		List<Estado> list = servico.buscarPorTodos();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> buscarPorCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = cidadeService.buscarPorEstado(estadoId);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

}
