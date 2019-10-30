package com.spdiframework.beans.factory;

import com.spdiframework.beans.factory.support.BeanDefinitionRegistry;
import com.spdiframework.beans.factory.support.DefaultBeanDefinitionRegistry;
import com.spdiframework.beans.metadata.BeanDefinition;
import com.spdiframework.beans.metadata.BeanScope;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hyungyu.lee@nhn.com
 */
public abstract class AbstractBeanFactory implements BeanFactory {

	private final BeanDefinitionRegistry beanDefinitionRegistry;
	private final SingletonRegistry singletonRegistry;

	public AbstractBeanFactory() {
		this(new DefaultBeanDefinitionRegistry());
	}

	public AbstractBeanFactory(BeanDefinitionRegistry beanDefinitionRegistry) {
		this.beanDefinitionRegistry = beanDefinitionRegistry;
		this.singletonRegistry = new SingletonRegistry();
	}


	@Override
	public <T> T getBean(String name) {
		BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(name);
		return getBean(beanDefinition);
	}

	@Override
	public <T> List<Object> getBeans(Class<T> type) {
		return	beanDefinitionRegistry.getBeanDefinition(type).stream()
				.map(this::getBean)
				.collect(Collectors.toList());
	}

	@Override
	public <T> T getBean(BeanDefinition beanDefinition) {
		// TODO Bean 생성 로직 작성
		if (beanDefinition.getBeanScope() == BeanScope.SINGLETON) {
			return null;
		} else if (beanDefinition.getBeanScope() == BeanScope.PROTOTYPE) {
			return null;
		}

		return null;
	}
}
