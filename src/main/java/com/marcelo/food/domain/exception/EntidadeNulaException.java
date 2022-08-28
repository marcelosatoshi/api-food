package com.marcelo.food.domain.exception;

public class EntidadeNulaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeNulaException(String msg) {
		super(msg);
	}

}
