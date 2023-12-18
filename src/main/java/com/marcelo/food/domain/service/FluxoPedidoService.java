package com.marcelo.food.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelo.food.domain.exception.NegocioException;
import com.marcelo.food.domain.model.Pedido;
import com.marcelo.food.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Transactional
	public void confirmar(String codigoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoId);

	pedido.confirmar();
	}

	@Transactional
	public void cancelado(String codigoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoId);
	
		pedido.cancelamento();
	}
	
	@Transactional
	public void entregue(String codigoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoId);

		pedido.entregue();
	}
	
	
}
