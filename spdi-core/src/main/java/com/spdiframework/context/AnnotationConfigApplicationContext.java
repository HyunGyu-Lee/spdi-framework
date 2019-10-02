package com.spdiframework.context;

import com.spdiframework.beans.streotype.Configuration;
import com.spdiframework.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spdiframework.beans.exception.NotConfigurableClassException;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.spdiframework.beans.parser.classconfig.ClassConfigurationParser;

public class AnnotationConfigApplicationContext extends ApplicationContext {
	
	private static final Logger logger = LoggerFactory.getLogger(AnnotationConfigApplicationContext.class);
	
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
			logger.info("Parse {}", configurable.getName());
			if(ReflectionUtils.isAnnotatedOnClass(configurable, Configuration.class))
			{
				ClassConfigurationParser.parseAndApply(metaData, configurable);
			}
			else
			{
				throw new NotConfigurableClassException("This class isn't configurable class", configurable);
			}
		}
		
		return metaData;
	}

	

}
