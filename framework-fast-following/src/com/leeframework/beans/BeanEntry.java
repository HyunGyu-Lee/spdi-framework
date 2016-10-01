package com.leeframework.beans;

public class BeanEntry {
	private Class<?> beanType;
	private String beanName;
	private Scope scope;
	
	public BeanEntry(){}
	
	public BeanEntry(Class<?> beanType, String beanName, Scope scope) {
		this.beanType = beanType;
		this.beanName = beanName;
		this.scope = scope;
	}

	public Class<?> getBeanType() {
		return beanType;
	}
	public void setBeanType(Class<?> beanType) {
		this.beanType = beanType;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public Scope getScope() {
		return scope;
	}
	public void setScope(Scope scope) {
		this.scope = scope;
	}
}
