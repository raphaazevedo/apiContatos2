package br.com.rapha.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ContatoPostRequestDto {

	private String nome;
	private String telefone;
	private String email;
	private UUID categoria_id;
}
