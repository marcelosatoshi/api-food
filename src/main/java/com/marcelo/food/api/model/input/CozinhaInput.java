package com.marcelo.food.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaInput {
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String nome;

}
