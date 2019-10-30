package com.spdiframework.beans.factory.support;

import com.spdiframework.beans.metadata.BeanDefinition;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String name);
	List<BeanDefinition> getBeanDefinition(Class<?> type);

}
