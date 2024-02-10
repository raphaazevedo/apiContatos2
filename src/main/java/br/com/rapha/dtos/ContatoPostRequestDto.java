package br.com.rapha.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContatoPostRequestDto {

	@Size(min = 5, max = 100, message = "O nome deve ter entre 8 e 100 caracteres.")
	@NotBlank(message = "O campo nome não pode ser vazio.")
	private String nome;
	
	@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{5}-\\d{4}",
			message = "Informe o telefone no formato '(99) 99999-9999'.")
	@NotBlank(message = "Este campo não pode ser vazio.")
	private String telefone;
	
	@Email(message="Informe um e-mail válido.")
	@NotBlank(message = "O campo e-mail não pode ser vazio")
	private String email;
	
	@NotNull(message = "Informe o ID do plano")
	private UUID categoria_id;
}
