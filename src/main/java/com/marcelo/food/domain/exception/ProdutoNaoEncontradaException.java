package com.marcelo.food.domain.exception;


public class ProdutoNaoEncontradaException extends EntidadeNaoEncontradaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}
	
	public	ProdutoNaoEncontradaException(Long produtoId ,Long restauranteId) {
		this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d " 
	 		+ produtoId,restauranteId));
 }

}
