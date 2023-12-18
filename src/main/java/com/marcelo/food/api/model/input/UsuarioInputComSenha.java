package com.marcelo.food.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputComSenha extends UsuarioInput  {
	

	@NotNull
	public String senha;

}
