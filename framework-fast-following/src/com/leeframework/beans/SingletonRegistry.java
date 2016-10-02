package com.leeframework.beans;

import java.util.HashMap;

import com.leeframework.beans.metadata.BeanEntry;
import com.leeframework.beans.metadata.BeanEntryObjectMapper;

public class SingletonRegistry {

	private HashMap<String, Object> registry;
	private BeanEntryObjectMapper entryObjectMapper = new BeanEntryObjectMapper();
	
	public SingletonRegistry() {
		registry = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName, Class<T> clazz) {
		return (T)registry.get(beanName);
	}
	
	public void registry(BeanEntry beanEntry) {
		registry.put(beanEntry.getBeanName(), entryObjectMapper.mapping(beanEntry));
	}

	public HashMap<String, Object> getRegistry() {
		return registry;
	}
	public BeanEntryObjectMapper getEntryObjectMapper() {
		return entryObjectMapper;
	}
}
