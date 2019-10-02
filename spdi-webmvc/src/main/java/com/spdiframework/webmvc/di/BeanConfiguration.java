package com.spdiframework.webmvc.di;

import java.util.ArrayList;

import com.spdiframework.webmvc.di.bean.Property;

public class BeanConfiguration extends Configuration {

	private String name;
	private String clazz;
	private ArrayList<Property> properties;

	public BeanConfiguration() {}
	
	public BeanConfiguration(String name, String clazz, ArrayList<Property> properties) {
		this.name = name;
		this.clazz = clazz;
		this.properties = properties;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public ArrayList<Property> getProperties() {
		return properties;
	}
	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "BeanConfiguration [name=" + name + ", clazz=" + clazz + ", properties=" + properties + "]";
	}
	
}
