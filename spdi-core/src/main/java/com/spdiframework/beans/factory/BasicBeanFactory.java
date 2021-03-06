package com.spdiframework.beans.factory;

import com.spdiframework.beans.metadata.BeanDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dlgusrb0808@gmail.com
 */
public class BasicBeanFactory implements BeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

	@Override
	public <T> T getBean(String name) {
		return null;
	}

	@Override
	public <T> List<T> getBeans(Class<T> type) {
		return null;
	}

	@Override
	public <T> T getBean(BeanDefinition beanDefinition) {
		return null;
	}

}
