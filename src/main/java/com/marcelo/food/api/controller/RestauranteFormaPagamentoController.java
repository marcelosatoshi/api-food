package com.marcelo.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.food.api.assembler.FormaPagamentoModelAssembler;
import com.marcelo.food.api.model.FormaPagamentoModel;
import com.marcelo.food.domain.model.Restaurante;
import com.marcelo.food.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	
	@GetMapping
	public List<FormaPagamentoModel> listarPagamentosRestaurante(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamentos());
	}
	
	@PutMapping("/{formaPagamentoId}") 
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void associarFormaPagamento(@PathVariable Long restauranteId , @PathVariable Long formaPagamentoId) {
		cadastroRestauranteService.adicionarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
	@DeleteMapping("/{formaPagamentoId}") 
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void desassociarFormaPagamento(@PathVariable Long restauranteId , @PathVariable Long formaPagamentoId) {
		cadastroRestauranteService.removerFormaPagamento(restauranteId, formaPagamentoId);
	}
 

 

}
