package com.spdiframework.beans.metadata;

import java.lang.reflect.Method;
import java.util.HashMap;

/***
 * @author dlgusrb0808@gmail.com
 */
public class BeanEntry {
	private Class<?> beanType;
	private String beanName;
	private BeanScope beanScope;
	private String initMethod;
	private String destroyMethod;
	private HashMap<String, BeanProperty> properties = new HashMap<>();
	private Method invocable;

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

	public BeanScope getScope() {
		return beanScope;
	}

	public void setScope(BeanScope beanScope) {
		this.beanScope = beanScope;
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

	public String getInitMethod() {
		return initMethod;
	}

	public void setInitMethod(String initMethod) {
		this.initMethod = initMethod;
	}

	public String getDestroyMethod() {
		return destroyMethod;
	}

	public void setDestroyMethod(String destroyMethod) {
		this.destroyMethod = destroyMethod;
	}

	public Method getInvocable() {
		return invocable;
	}

	public void setInvocable(Method invocable) {
		this.invocable = invocable;
	}

}
