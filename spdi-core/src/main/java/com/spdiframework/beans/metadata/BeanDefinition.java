package com.spdiframework.beans.metadata;

/**
 * @author dlgusrb0808@gmail.com
 */
public class BeanDefinition {
	private Class<?> beanType;
	private String beanName;
	private BeanScope beanScope;

	public Class<?> getBeanType() {
		return beanType;
	}

	public String getBeanName() {
		return beanName;
	}

	public BeanScope getBeanScope() {
		return beanScope;
	}
}
