package com.leeframework.context;

import com.leeframework.beans.factory.AbstractBeanFactory;
import com.leeframework.beans.factory.BeanFactory;
import com.leeframework.beans.metadata.BeanFactoryMetaData;
import com.leeframework.context.exception.NotLoadedException;
import com.leeframework.context.support.LifeCycle;

public abstract class ApplicationContext extends LifeCycle {
	
	private AbstractBeanFactory beanFactory;
	
	public ApplicationContext(){}
	
	public abstract BeanFactoryMetaData createBeanFactoryMetaDataStrategy();
	
	public <T> T getBean(String beanName, Class<T> clazz) { 
		if(isLoaded())
		{
			return beanFactory.getBean(beanName, clazz);
		}
		else
		{
			throw new NotLoadedException("컨텍스트가 로드되지 않았습니다.");
		}
	}
	
	public void setBeanFactory(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	public AbstractBeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	@Override
	public void load() {
		if(isLoadHooking())loadHook();
		
		beanFactory = new BeanFactory(createBeanFactoryMetaDataStrategy());
		beanFactory.setApplicationContext(this);
		
		super.setLoaded(true);
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
	
	public void close() {
		shutdown();
	}
}
