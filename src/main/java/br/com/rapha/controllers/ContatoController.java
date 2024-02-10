package br.com.rapha.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rapha.dtos.ContatoPostRequestDto;
import br.com.rapha.dtos.ContatoPutRequestDto;
import br.com.rapha.entities.Categoria;
import br.com.rapha.entities.Contato;
import br.com.rapha.repositories.CategoriaRepository;
import br.com.rapha.repositories.ContatoRepository;
import jakarta.validation.Valid;

@RestController 
@RequestMapping(value = "/api/contatos")
public class ContatoController {

	@GetMapping
	public ResponseEntity<List<Contato>> getAll()throws Exception{
		
		try {
			
			
			ContatoRepository contatoRepository = new ContatoRepository();
			
			List<Contato> contatos = contatoRepository.findAll();	
			
			if(contatos.size() == 0) {
				
				//HTTP 204 NO CONTENT
				return ResponseEntity.status(204)
						.body(null);
				
			}
			
			// HTTP 200 - OK
			return ResponseEntity.status(200)
					.body(contatos);
			
			
			
		} catch (Exception e) {
			
			// HTTP 500 INTERNAL ERROR SERVER
			return ResponseEntity.status(500)
					.body(null);
			
		}
		
		
		
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Contato> getById(@PathVariable("id") UUID id)throws Exception{
		
		try {
			
			
			ContatoRepository contatoRepository = new ContatoRepository();
			
			Contato contato = contatoRepository.findById(id);
			
			
			
			if (contato == null) {
				
				//HTTP 204 - NO CONTENT
				return ResponseEntity.status(204)
						.body(null);
				
			}
			// HTTP 200 - OK
			return ResponseEntity.status(200)
					.body(contato);
			
			
		} catch (Exception e) {

			
			// HTTP 500 INTERNAL ERROR SERVER
			return ResponseEntity.status(500)
					.body(null);
		}
		
		
		
		
	}
	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid ContatoPostRequestDto dto) {
		
		try {
			
			Contato contato = new Contato();
			
			contato.setId(UUID.randomUUID());
			contato.setNome(dto.getNome());
			contato.setTelefone(dto.getTelefone());
			contato.setEmail(dto.getEmail());
			
			CategoriaRepository categoriaRepository = new CategoriaRepository();
			
			Categoria categoria = categoriaRepository.findById(dto.getCategoria_id());
			
			if(categoria == null) {
				
				// BAD REQUEST HTTP 400
				return ResponseEntity.status(400)
						.body("Categoria não encontrada, informe o ID.");
				
			}
			
			contato.setCategoria(categoria);
			
			ContatoRepository contatoRepository = new ContatoRepository();
			
			contatoRepository.insert(contato);
			
				//HTTP 201 CREATED
				return ResponseEntity.status(201)
						.body("Contato cadastrado com sucesso!");
			
			
			
			
		}catch (Exception e) {

			return ResponseEntity.status(500)
					.body(e.getMessage());
			
		}
		
	}
	@PutMapping
	public ResponseEntity<String> put(@RequestBody @Valid ContatoPutRequestDto dto) {
		
		try {
			ContatoRepository contatoRepository = new ContatoRepository();
			
			Contato contato = contatoRepository.findById(dto.getId());
			
			if (contato == null) {
				
				//BAD REQUEST 400
				return ResponseEntity.status(400)
						.body("Contato não encontrado, verifique o ID");
				
			}
			
			CategoriaRepository categoriaRepository = new CategoriaRepository();
			Categoria categoria = categoriaRepository.findById(dto.getCategoria_id());
			
			if (categoria == null) {
				
				//BAD REQUEST 400
				return ResponseEntity.status(400)
						.body("Categoria não encontrada, verifique o ID");

			}
			
			contato.setNome(dto.getNome());
			contato.setEmail(dto.getEmail());
			contato.setTelefone(dto.getTelefone());
			contato.setCategoria(categoria);
			
			contatoRepository.update(contato);
			
			// HTTP 200 - OK
			return ResponseEntity.status(200)
					.body("Contado atualizado com sucesso!!");
									
		} catch (Exception e) {

			// HTTP 500 INTERNAL ERROR SERVER
			return ResponseEntity.status(500)
					.body(e.getMessage());
			
		}
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
		
		try {
			
			ContatoRepository contatoRepository = new ContatoRepository();
			Contato contato = contatoRepository.findById(id);
			
			if (contato == null) {
				
				// HTTP 400 - BAD REQUEST
				return ResponseEntity.status(400)
						.body("Contato não encontrado, verifique o ID.");
				
				
			}
			contatoRepository.delete(contato);
			
			//HTTP 200 - OK
			return ResponseEntity.status(200)
					.body("Contato excluido com sucesso!!");
			
			
			
		} catch (Exception e) {
			
			//HTTP 500 INTERNAL ERROR SERVER
			return ResponseEntity.status(500)
					.body(e.getMessage());
		}
		
	}
	
}
