package br.com.rapha.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rapha.entities.Categoria;
import br.com.rapha.repositories.CategoriaRepository;

@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {
	
	@GetMapping
	public List<Categoria> getAll()throws Exception{
		
		CategoriaRepository categoriaRepository = new CategoriaRepository();
		
		return categoriaRepository.findAll();
	}
	@GetMapping("{id}")
	public Categoria findById(@PathVariable("id") UUID id)throws Exception{
		
		CategoriaRepository categoriaRepository = new CategoriaRepository();
		
		
		
		return categoriaRepository.findById(id);
		
		
	}

}
