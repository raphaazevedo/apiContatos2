package br.com.rapha.entities;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Categoria {
	private UUID id;
	private String nome;
	private List<Contato> contatos;
}
