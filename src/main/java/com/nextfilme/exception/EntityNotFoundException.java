package com.nextfilme.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public EntityNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}