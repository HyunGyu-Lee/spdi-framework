package com.spdiframework.beans.factory;

import com.spdiframework.beans.metadata.BeanDefinition;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;

import java.util.List;

/***
 * @author dlgusrb0808@gmail.com
 */
public class OldBeanFactory extends OldAbstractBeanFactory {
	BeanFactoryMetaData beanFactoryMetaData;
	
	public OldBeanFactory(BeanFactoryMetaData beanFactoryMetadata) {
		super(beanFactoryMetadata);
	}

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
