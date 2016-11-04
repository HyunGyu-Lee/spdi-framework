package com.leeframework.context.exception;

public class NotLoadedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotLoadedException(String msg) {
		super(msg);
	}
	
}
