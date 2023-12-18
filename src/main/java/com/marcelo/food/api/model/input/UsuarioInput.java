package com.marcelo.food.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	public String nome;
	
	@NotBlank
	@Email
	public String email;
}
