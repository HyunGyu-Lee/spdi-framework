package com.spdiframework.beans.factory;

import com.spdiframework.beans.metadata.BeanDefinition;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
public interface BeanFactory {

	public <T> T getBean(String name);

	public <T> List<T> getBeans(Class<T> type);

	public <T> T getBean(BeanDefinition beanDefinition);

}
