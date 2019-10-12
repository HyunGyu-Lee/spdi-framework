package com.spdiframework.context;

import com.spdiframework.beans.factory.OldAbstractBeanFactory;
import com.spdiframework.beans.factory.OldBeanFactory;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.spdiframework.context.exception.NotLoadedException;
import com.spdiframework.context.support.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @author dlgusrb0808@gmail.com
 */
public abstract class OldApplicationContext extends LifeCycle {

	private OldAbstractBeanFactory beanFactory;

	public OldApplicationContext(OldAbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	private static final Logger logger = LoggerFactory.getLogger(OldApplicationContext.class);

	public OldApplicationContext() {
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

	public void setBeanFactory(OldAbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public OldAbstractBeanFactory getBeanFactory() {
		return beanFactory;
	}

	@Override
	public void load() {
		if (isLoadHooking())
			loadHook();

		beanFactory = new OldBeanFactory(createBeanFactoryMetaDataStrategy());

		super.setLoaded(true);
	}

	@Override
	public void shutdown() {
		if (isShutdownHooking()) {
			shutdownHook();
		}
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
