package com.marcelo.food.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.food.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(value = "/pedidos/{codigoId}")
public class FluxoPedidoController {
	
	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmaçao(@PathVariable String codigoId) {
		fluxoPedidoService.confirmar(codigoId);
	}
	
	@PutMapping("/cancelado") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelado(@PathVariable String codigoId) {
		fluxoPedidoService.cancelado(codigoId);
	}
	

	@PutMapping("/entregue") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregue(@PathVariable String codigoId) {
		fluxoPedidoService.entregue(codigoId);
	}

}
