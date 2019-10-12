package com.spdiframework.beans.factory;

import com.spdiframework.beans.metadata.BeanFactoryMetaData;

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
	public <T> T getBean(Class<T> type) {
		return null;
	}
}
