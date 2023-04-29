package com.marcelo.food.api.model.mixin;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marcelo.food.domain.model.Cozinha;
import com.marcelo.food.domain.model.Endereco;
import com.marcelo.food.domain.model.FormaPagamento;
import com.marcelo.food.domain.model.Produto;

public abstract  class RestauranteMixin {
	
	@JsonIgnore
	private Endereco endereco;
	
	//@JsonIgnore
	private OffsetDateTime dataCadastro;
	
	//@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamentos = new ArrayList<>();
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
		private Cozinha cozinha;
	
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();
	
	

}
