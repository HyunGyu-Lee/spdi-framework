package com.hst.spdiframework.beans.factory;

import com.hst.spdiframework.beans.metadata.BeanFactoryMetaData;

public class BeanFactory extends AbstractBeanFactory {
	
	BeanFactoryMetaData beanFactoryMetaData;
	
	public BeanFactory(BeanFactoryMetaData beanFactoryMetadata) {
		super(beanFactoryMetadata);
	}

}
