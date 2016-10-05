package com.leeframework.context;

import com.leeframework.beans.factory.AbstractBeanFactory;
import com.leeframework.beans.factory.BeanFactory;
import com.leeframework.beans.metadata.BeanFactoryMetaData;
import com.leeframework.context.support.LifeCycle;

public abstract class ApplicationContext extends LifeCycle {
	
	private AbstractBeanFactory beanFactory;
	
	public ApplicationContext(){}
	
	public abstract BeanFactoryMetaData createBeanFactoryMetaDataStrategy();
	
	public <T> T getBean(String beanName, Class<T> clazz) { 
		return beanFactory.getBean(beanName, clazz);
	}
	
	public void setBeanFactory(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	public AbstractBeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	@Override
	public void initailize() {
		if(isInitailizeHooking())initailizeHook();
		beanFactory = new BeanFactory(createBeanFactoryMetaDataStrategy());
		beanFactory.setApplicationContext(this);
	}

	@Override
	public void shutdown() {
		if(isShutdownHooking())shutdownHook();
		beanFactory.destroy();
	}
	
	@Override
	public void refresh() {
		beanFactory.refresh();
	}
	
	@Override
	public void close() {
		shutdown();
	}
}
