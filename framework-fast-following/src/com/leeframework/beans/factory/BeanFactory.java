package com.leeframework.beans.factory;

import com.leeframework.beans.metadata.BeanFactoryMetaData;

public class BeanFactory extends AbstractBeanFactory {
	
	BeanFactoryMetaData beanFactoryMetaData;
	
	public BeanFactory(BeanFactoryMetaData beanFactoryMetadata) {
		super(beanFactoryMetadata);
	}

}
