package com.hst.spdiframework.beans.exception;

public class NotConfigurableClassException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotConfigurableClassException(String msg, Class<?> clazz) {
		super(msg+" : "+clazz.getName());
	}
	
}
