package com.leeframework.context;

import com.leeframework.beans.annotation.Bean;
import com.leeframework.beans.annotation.Configuration;
import com.leeframework.beans.exception.NotConfigurableClassException;
import com.leeframework.beans.metadata.BeanFactoryMetaData;
import com.leeframework.utils.ReflectionUtils;

public class AnnotationConfigApplicationContext extends ApplicationContext {
	
	private Class<?>[] configurables;
	
	public AnnotationConfigApplicationContext(){}
	
	public AnnotationConfigApplicationContext(Class<?>...configurables) {
		this.configurables = configurables;
		load();
		refresh();
	}
	
	public Class<?>[] getConfigurables() {
		return configurables;
	}

	public void setConfigurables(Class<?>[] configurables) {
		this.configurables = configurables;
	}

	@Override
	protected BeanFactoryMetaData createBeanFactoryMetaDataStrategy() {
		BeanFactoryMetaData metaData = new BeanFactoryMetaData();
		
		for(Class<?> configurable : configurables)
		{
			if(ReflectionUtils.isAnnotatedOnClass(configurable, Configuration.class))
			{
				ReflectionUtils.getAnnotatedMethods(configurable, Bean.class);
			}
			else
			{
				throw new NotConfigurableClassException("This class isn't configurable class", configurable);
			}
		}
		
		return metaData;
	}

	

}
