package com.spdiframework.beans.exception;

/***
 * @author dlgusrb0808@gmail.com
 */
public class NotConfigurableClassException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotConfigurableClassException(String msg, Class<?> clazz) {
		super(msg + " : " + clazz.getName());
	}

}
