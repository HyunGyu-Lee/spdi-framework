package com.spdiframework.beans.factory;

import com.spdiframework.beans.lifecycle.DisposableBean;
import com.spdiframework.beans.lifecycle.InitailizingBean;
import com.spdiframework.context.ApplicationContext;
import com.spdiframework.context.ApplicationContextAware;
import com.spdiframework.utils.ReflectionUtils;
import com.spdiframework.beans.aware.BeanFactoryAware;
import com.spdiframework.beans.aware.BeanNameAware;
import com.spdiframework.beans.exception.NoSuchBeanException;
import com.spdiframework.beans.metadata.BeanEntry;
import com.spdiframework.beans.metadata.BeanEntryObjectMapper;
import com.spdiframework.beans.metadata.BeanFactoryMetaData;
import com.spdiframework.beans.metadata.BeanReference;
import com.spdiframework.beans.metadata.Scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @author dlgusrb0808@gmail.com
 */
public abstract class AbstractBeanFactory extends BeanEntryObjectMapper implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(AbstractBeanFactory.class);

	private ApplicationContext applicationContext;
	private BeanFactoryMetaData beanFactoryMetaData;
	private SingletonRegistry singletonRegistry = new SingletonRegistry(this);

	AbstractBeanFactory(BeanFactoryMetaData beanFactoryMetaData) {
		load(beanFactoryMetaData);
	}

	private void load(BeanFactoryMetaData beanFactoryMetaData) {
		logger.info("Load metadata {}", beanFactoryMetaData);
		this.beanFactoryMetaData = beanFactoryMetaData;
	}

	public void refresh() {
		for (String beanName : beanFactoryMetaData.getBeanEntries().keySet()) {
			BeanEntry entry = beanFactoryMetaData.getBeanEntries().get(beanName);
			if (entry.getScope() == null)
				beanFactoryMetaData.getBeanEntries().get(beanName).setScope(Scope.SINGLETON);

			if (entry.getScope().equals(Scope.SINGLETON)) {
				logger.info("{}({}) registed at Singleton registry", beanName, entry.getBeanType().getSimpleName());
				singletonRegistry.registry(beanFactoryMetaData.getBeanEntries().get(beanName));
			}
		}
	}

	public BeanFactoryMetaData getBeanFactoryMetaData() {
		return beanFactoryMetaData;
	}

	public void setBeanFactoryMetaData(BeanFactoryMetaData beanFactoryMetaData) {
		this.beanFactoryMetaData = beanFactoryMetaData;
	}

	public <T> T getBean(String beanName, Class<T> clazz) {
		BeanEntry beanEntry = beanFactoryMetaData.getEntry(beanName);
		BeanReference reference = beanFactoryMetaData.getReference(beanName);

		if (beanEntry == null)
			throw new NoSuchBeanException(beanName);

		T beanObject = getBeanByScope(beanEntry);
		processBeanReference(beanObject, reference);
		processAware(beanObject, beanEntry);
		processBeanInitiating(beanObject, beanEntry);

		return beanObject;
	}

	@SuppressWarnings("unchecked")
	private <T> T getBeanByScope(BeanEntry beanEntry) {
		if (beanEntry.getScope().equals(Scope.SINGLETON)) {
			return (T) singletonRegistry.getBean(beanEntry.getBeanName(), beanEntry.getBeanType());
		} else if (beanEntry.getScope().equals(Scope.PROTOTYPE)) {
			return (T) this.mapping(beanEntry, beanEntry.getBeanType());
		} else {
			return (T) singletonRegistry.getBean(beanEntry.getBeanName(), beanEntry.getBeanType());
		}
	}

	private <T> void processAware(T beanObject, BeanEntry beanEntry) {
		if (ReflectionUtils.isImplements(beanEntry.getBeanType(), BeanNameAware.class)) {
			ReflectionUtils.invoke(beanObject, "setBeanName", beanEntry.getBeanName());
		}

		if (ReflectionUtils.isImplements(beanEntry.getBeanType(), BeanFactoryAware.class)) {
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanEntry.getBeanType(), "setBeanFactory",
					AbstractBeanFactory.class), beanObject, this);
		}

		if (ReflectionUtils.isImplements(beanEntry.getBeanType(), ApplicationContextAware.class)) {
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanEntry.getBeanType(), "setApplicationContext",
					ApplicationContext.class), beanObject, applicationContext);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> void processBeanReference(T beanObject, BeanReference reference) {
		if (reference != null) {
			BeanEntry refEntry = beanFactoryMetaData.getEntry(reference.getRefName());
			T refBean = (T) getBean(refEntry.getBeanName(), refEntry.getBeanType());
			ReflectionUtils.setField(beanObject, reference.getInjectField(), refBean);
		}
	}

	/***
	 * Invoke bean initialize hook method
	 *
	 * @param beanObject target bean
	 * @param beanEntry target bean definition
	 */
	private <T> void processBeanInitiating(T beanObject, BeanEntry beanEntry) {
		Class<?> beanType = beanEntry.getBeanType();

		// TODO / [기존 기능 추가] / @PreDestroy 어노테이션 구현 필요
		// ReflectionUtils.invoke(ReflectionUtils.getAnnotatedMethod(beanType, PostConstruct.class), beanObject);

		if (ReflectionUtils.isImplements(beanType, InitailizingBean.class)) {
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanType, "afterPropertiesSet"), beanObject);
		}

		if (beanEntry.getInitMethod() != null) {
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanType, beanEntry.getInitMethod()), beanObject);
		}
	}

	private <T> void processBeanDisposing(T beanObject, BeanEntry beanEntry) {
		Class<?> beanType = beanEntry.getBeanType();

		if (beanEntry.getDestroyMethod() != null) {
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanType, beanEntry.getDestroyMethod()), beanObject);
		}

		if (ReflectionUtils.isImplements(beanType, DisposableBean.class)) {
			ReflectionUtils.invoke(ReflectionUtils.findMethod(beanType, "destroy"), beanObject);
		}
		// TODO / [기존 기능 추가] @PreDestroy 어노테이션 구현 필요
		// ReflectionUtils.invoke(ReflectionUtils.getAnnotatedMethod(beanType, PreDestroy.class), beanObject);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void destroy() {
		if (singletonRegistry != null) {
			for (String beanName : beanFactoryMetaData.getBeanEntries().keySet()) {
				if (beanFactoryMetaData.getBeanEntries().get(beanName).getScope().equals(Scope.SINGLETON)) {
					destroyBean(beanName);
				}
			}
		}
	}

	public void destroyBean(String beanName) {
		BeanEntry entry = beanFactoryMetaData.getEntry(beanName);
		processBeanDisposing(singletonRegistry.getBean(beanName, entry.getBeanType()), entry);
		//beanFactoryMetaData.getBeanEntries().remove(beanName);
		singletonRegistry.destroySingleton(beanName);
	}

}
