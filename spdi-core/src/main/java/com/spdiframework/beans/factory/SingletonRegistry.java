package com.spdiframework.beans.factory;

import com.spdiframework.beans.metadata.BeanDefinition;
import com.spdiframework.beans.metadata.BeanEntryObjectMapper;

import java.util.HashMap;
import java.util.Map;

/***
 * @author dlgusrb0808@gmail.com
 */
public class SingletonRegistry {

	private Map<String, Object> singletonBeanRegistry;

	SingletonRegistry() {
		singletonBeanRegistry = new HashMap<>();
	}

	public void registerSingleton(String beanName, Object instance) {
		singletonBeanRegistry.put(beanName, instance);
	}

	public Object getSingleton(String beanName) {
		return singletonBeanRegistry.get(beanName);
	}
}
