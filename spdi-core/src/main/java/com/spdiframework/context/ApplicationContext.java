package com.spdiframework.context;

import com.spdiframework.context.exception.NotLoadedException;
import com.spdiframework.context.support.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spdiframework.beans.factory.AbstractBeanFactory;
import com.spdiframework.beans.factory.BeanFactory;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;

public abstract class ApplicationContext extends LifeCycle {
	
	private AbstractBeanFactory beanFactory;

	public ApplicationContext(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	private static final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
	
	public ApplicationContext() {
		logger.info("Root Context Start");
	}
	
	protected abstract BeanFactoryMetaData createBeanFactoryMetaDataStrategy();
	
	public <T> T getBean(String beanName, Class<T> clazz) {
		if (isLoaded()) {
			return beanFactory.getBean(beanName, clazz);
		} else {
			throw new NotLoadedException("ApplicationContext not loaded");
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
