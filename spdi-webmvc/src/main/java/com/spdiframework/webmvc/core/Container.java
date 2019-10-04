package com.spdiframework.webmvc.core;

import java.util.ArrayList;
import java.util.HashMap;

import com.spdiframework.webmvc.di.BeanConfiguration;
import com.spdiframework.webmvc.di.BeanReference;
import com.spdiframework.webmvc.di.ContainerConfiguration;
import com.spdiframework.webmvc.di.bean.Property;
import com.spdiframework.webmvc.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Container {

	private HashMap<String, Object> beans;
	private ArrayList<BeanReference> references;
	private ContainerConfiguration configuration;

	private static final Logger logger = LoggerFactory.getLogger(Container.class);

	public Container() {
	}

	public Container(String configurationPath, String containerType) {
		beans = new HashMap<>();
		references = new ArrayList<>();
		logger.info("Container type - {}", containerType);
		logger.info("Load type - {}", "normal");
		configuration = ContainerFactory.getContainerConfiguration(configurationPath);
		loadAllBean(configuration);
	}

	public Container(ContainerConfiguration configuration, String containerType) {
		beans = new HashMap<>();
		references = new ArrayList<>();
		this.configuration = configuration;
		logger.info("Container type - {}", containerType);
		logger.info("Load type - {}", "normal");
		loadAllBean(configuration);
	}

	public void lazyLoad(String configurationPath, String containerType) {
		beans = new HashMap<>();
		references = new ArrayList<>();
		logger.info("Container type - {}", containerType);
		logger.info("Load type - {}", "Lazy load");
		configuration = ContainerFactory.getContainerConfiguration(configurationPath);
		loadAllBean(configuration);
	}

	public void loadAllBean(ContainerConfiguration configuration) {
		ArrayList<BeanConfiguration> beanConfs = configuration.getBeanConfigurations();

		for (BeanConfiguration beanConf : beanConfs) {
			String beanName = beanConf.getName();
			String clazzName = beanConf.getClazz();

			Class<?> clazz = ReflectionUtils.findClass(clazzName);
			if (clazz == null) {
				logger.error("Cannot find class : " + clazzName);
				continue;
			}
			Object instance = ReflectionUtils.createInstance(clazz);

			for (Property property : beanConf.getProperties()) {
				String propertyName = property.getName();
				String propertyValue = property.getValue();
				String propertyRef = property.getRef();

				if (propertyValue == null) {
					Object someBean = beans.get(propertyRef);
					if (someBean == null) {
						references.add(new BeanReference(beanName, propertyRef, propertyName));
					} else {
						ReflectionUtils.set(instance, propertyName, someBean);
					}
				} else if (propertyRef == null) {
					ReflectionUtils.set(instance, propertyName, propertyValue);
				} else {
					System.out.println("���� �߻����Ѿ���");
				}
			}

			beans.put(beanName, instance);
			logger.info("Load Bean - [{} - {}]", beanName, clazz);
		}
		referenceMatch(references);
	}

	public void referenceMatch(ArrayList<BeanReference> references) {
		for (BeanReference reference : references) {
			Object targetBean = beans.get(reference.getTarget());
			Object injectedBean = beans.get(reference.getRefName());
			if (injectedBean == null) {
				logger.error("Cannot inject bean [{}]", reference.getRefName());
			} else {
				ReflectionUtils.set(targetBean, reference.getInjectLocation(), injectedBean);
				logger.info("Inject Bean - [{} to {}]", reference.getRefName(), reference.getTarget());
			}
		}
		logger.info("Dependency Injecting Finish");
	}

	public HashMap<String, Object> getBeans() {
		return beans;
	}

	public void setBeans(HashMap<String, Object> loadedBeans) {
		this.beans = loadedBeans;
	}

	public ContainerConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ContainerConfiguration configuration) {
		this.configuration = configuration;
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName, Class<T> clazz) {
		return (T) beans.get(beanName);
	}

}
