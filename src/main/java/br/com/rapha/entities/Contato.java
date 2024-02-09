package br.com.rapha.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Contato {

	private UUID id;
	private String nome;
	private String telefone;
	private String email;
	private Categoria categoria;
}
