package com.marcelo.food.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

	public GrupoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public GrupoNaoEncontradoException(Long id) {
		this(String.format("Não existe nenhum cadastro com código %d ", id));
	}
	

	public GrupoNaoEncontradoException(Long id, Long grupoId) {
		this(String.format("Não existe nenhum cadastro com código %d ", id, grupoId));
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
