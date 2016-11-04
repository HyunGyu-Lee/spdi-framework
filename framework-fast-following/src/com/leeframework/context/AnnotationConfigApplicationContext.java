package com.leeframework.context;

import com.leeframework.beans.metadata.BeanFactoryMetaData;

public class AnnotationConfigApplicationContext extends ApplicationContext {
	
	public AnnotationConfigApplicationContext(Class<?>...configurations) {
		
	}
	
	@Override
	public BeanFactoryMetaData createBeanFactoryMetaDataStrategy() {
		return null;
	}

	@Override
	public void load() {
		
	}

}
