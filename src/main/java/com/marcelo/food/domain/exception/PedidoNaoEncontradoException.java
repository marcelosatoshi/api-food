package com.marcelo.food.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

	
	
	public PedidoNaoEncontradoException(String codigoId) {
		super(String.format("Não existe um pedido com código %s", codigoId));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
