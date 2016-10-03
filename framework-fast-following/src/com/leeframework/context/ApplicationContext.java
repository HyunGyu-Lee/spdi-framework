package com.leeframework.context;

import com.leeframework.beans.factory.AbstractBeanFactory;
import com.leeframework.beans.factory.BeanFactory;
import com.leeframework.beans.metadata.BeanFactoryMetaData;

public abstract class ApplicationContext {
	
	private AbstractBeanFactory beanFactory;
	
	public void initailize() {
		beanFactory = new BeanFactory(createBeanFactoryMetaDataStrategy());
		beanFactory.setApplicationContext(this);
	}
	
	public abstract BeanFactoryMetaData createBeanFactoryMetaDataStrategy();
	
	public <T> T getBean(String beanName, Class<T> clazz) { 
		return beanFactory.getBean(beanName, clazz);
	}
	
	public AbstractBeanFactory getBeanFactory() {
		return beanFactory;
	}
}
