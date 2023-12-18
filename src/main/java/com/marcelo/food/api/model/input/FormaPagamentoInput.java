package com.marcelo.food.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormaPagamentoInput {
	
	
	@NotNull
	private String descricao;
	

}
