package com.spdiframework.context;

import com.spdiframework.beans.exception.NotConfigurableClassException;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.spdiframework.beans.parser.classconfig.ClassConfigurationParser;
import com.spdiframework.beans.streotype.Configuration;
import com.spdiframework.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @author dlgusrb0808@gmail.com
 */
public class AnnotationConfigOldApplicationContext extends OldApplicationContext implements ApplicationContext {

	private static final Logger logger = LoggerFactory.getLogger(AnnotationConfigOldApplicationContext.class);

	private Class<?>[] configurables;

	public AnnotationConfigOldApplicationContext(Class<?>... configurables) {
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

		for (Class<?> configurable : configurables) {
			logger.info("Parse {}", configurable.getName());
			if (ReflectionUtils.isAnnotatedOnClass(configurable, Configuration.class)) {
				ClassConfigurationParser.parseAndApply(metaData, configurable);
			} else {
				throw new NotConfigurableClassException("This class isn't configurable class", configurable);
			}
		}

		return metaData;
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
