package br.com.rapha.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rapha.entities.Contato;
import br.com.rapha.repositories.ContatoRepository;

@RestController 
@RequestMapping(value = "/api/contatos")
public class ContatoController {

	@GetMapping
	public List<Contato> getAll()throws Exception{
		
		ContatoRepository contatoRepository = new ContatoRepository();
		
		return contatoRepository.findAll();
		
	}
	
	@GetMapping("{id}")
	public Contato getById(@PathVariable("id") UUID id)throws Exception{
		
		ContatoRepository contatoRepository = new ContatoRepository();
		
		
		return contatoRepository.findById(id);
		
		
	}
	
}
