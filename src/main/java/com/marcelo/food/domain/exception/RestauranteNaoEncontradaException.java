package com.marcelo.food.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradaException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}

	public RestauranteNaoEncontradaException(Long restauranteId) {
        this(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
    }   

}
