package com.spdiframework.beans.factory;

import java.util.HashMap;

import com.spdiframework.beans.metadata.BeanEntry;
import com.spdiframework.beans.metadata.BeanEntryObjectMapper;

public class SingletonRegistry {

	private HashMap<String, Object> registry;
	private BeanEntryObjectMapper entryObjectMapper;
	
	public SingletonRegistry(BeanEntryObjectMapper entryObjectMapper) {
		registry = new HashMap<>();
		this.entryObjectMapper = entryObjectMapper;
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

	public void destroySingleton(String beanName) {
		registry.remove(beanName);
	}
}
