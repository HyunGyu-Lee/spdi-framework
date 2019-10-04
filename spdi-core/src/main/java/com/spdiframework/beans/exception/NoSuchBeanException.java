package com.spdiframework.beans.exception;

/***
 * @author dlgusrb0808@gmail.com
 */
public class NoSuchBeanException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchBeanException(String beanName) {
		super("Cannot find Bean : " + beanName);
	}

}
