package com.marcelo.food.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String nome;


}
