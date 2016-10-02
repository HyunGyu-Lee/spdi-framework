package com.leeframework.beans.metadata;

import java.util.HashMap;

public class BeanEntry {
	private Class<?> beanType;
	private String beanName;
	private Scope scope;
	private HashMap<String,BeanProperty> properties = new HashMap<String,BeanProperty>();
	
	public BeanEntry() {}
	
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
	public void addProperty(BeanProperty property) {
		properties.put(property.getName(), property);
	}
	public BeanProperty getProperty(String propertyName) {
		return properties.get(propertyName);
	}
	public HashMap<String, BeanProperty> getProperties() {
		return properties;
	}
	public void setProperties(HashMap<String, BeanProperty> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "BeanEntry [beanType=" + beanType + ", beanName=" + beanName + ", scope=" + scope + ", properties="
				+ properties + "]";
	}
}
