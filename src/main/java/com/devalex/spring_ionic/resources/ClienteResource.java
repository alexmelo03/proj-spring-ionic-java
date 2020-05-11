package com.devalex.spring_ionic.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devalex.spring_ionic.domain.Cliente;
import com.devalex.spring_ionic.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService servico;
	
		
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarIdResource(@PathVariable Integer id) {
    	Cliente obj = servico.buscarPorId(id);
    	return ResponseEntity.ok().body(obj);

	}
}
