package com.marcelo.food.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {

	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
	
	
	@Valid
	@Size(min = 1)
	@NotNull
	private  List<ItemPedidoInput>  itens;
}