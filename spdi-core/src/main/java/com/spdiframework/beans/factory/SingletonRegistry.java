package com.spdiframework.beans.factory;

import java.util.HashMap;

import com.spdiframework.beans.metadata.BeanEntry;
import com.spdiframework.beans.metadata.BeanEntryObjectMapper;

/***
 * @author dlgusrb0808@gmail.com
 */
public class SingletonRegistry {

	private HashMap<String, Object> registry;
	private BeanEntryObjectMapper entryObjectMapper;

	SingletonRegistry(BeanEntryObjectMapper entryObjectMapper) {
		registry = new HashMap<>();
		this.entryObjectMapper = entryObjectMapper;
	}

	@SuppressWarnings("unchecked")
	<T> T getBean(String beanName, Class<T> clazz) {
		return (T) registry.get(beanName);
	}

	void registry(BeanEntry beanEntry) {
		registry.put(beanEntry.getBeanName(), entryObjectMapper.mapping(beanEntry));
	}

	public HashMap<String, Object> getRegistry() {
		return registry;
	}

	public BeanEntryObjectMapper getEntryObjectMapper() {
		return entryObjectMapper;
	}

	void destroySingleton(String beanName) {
		registry.remove(beanName);
	}
}
