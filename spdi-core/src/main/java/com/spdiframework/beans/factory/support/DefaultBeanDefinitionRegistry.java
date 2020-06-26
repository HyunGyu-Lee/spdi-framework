package com.spdiframework.beans.factory.support;

import com.spdiframework.beans.metadata.BeanDefinition;

import java.util.List;

/**
 * @author hyungyu.lee@nhn.com
 */
public class DefaultBeanDefinitionRegistry implements BeanDefinitionRegistry {
	@Override
	public BeanDefinition getBeanDefinition(String name) {
		return null;
	}

	@Override
	public List<BeanDefinition> getBeanDefinition(Class<?> type) {
		return null;
	}
}
