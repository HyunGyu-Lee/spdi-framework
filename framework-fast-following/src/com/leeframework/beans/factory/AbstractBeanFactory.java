package com.leeframework.beans.factory;

import com.leeframework.beans.SingletonRegistry;
import com.leeframework.beans.aware.BeanFactoryAware;
import com.leeframework.beans.aware.BeanNameAware;
import com.leeframework.beans.exception.NoSuchBeanException;
import com.leeframework.beans.metadata.BeanEntry;
import com.leeframework.beans.metadata.BeanFactoryMetaData;
import com.leeframework.beans.metadata.BeanReference;
import com.leeframework.beans.metadata.Scope;
import com.leeframework.context.ApplicationContext;
import com.leeframework.context.ApplicationContextAware;
import com.leeframework.utils.ReflectionUtils;

public abstract class AbstractBeanFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	private BeanFactoryMetaData beanFactoryMetaData;
	private SingletonRegistry singletonRegistry = new SingletonRegistry();

	public AbstractBeanFactory(BeanFactoryMetaData beanFactoryMetadata) {
		this.beanFactoryMetaData = beanFactoryMetadata;
		for(String key : beanFactoryMetadata.getBeanEntries().keySet())
		{
			if(beanFactoryMetadata.getBeanEntries().get(key).getScope().equals(Scope.SINGLETON))
			{
				singletonRegistry.registry(beanFactoryMetadata.getBeanEntries().get(key));
			}
		}
	}
	public BeanFactoryMetaData getBeanFactoryMetaData() {
		return beanFactoryMetaData;
	}
	public void setBeanFactoryMetaData(BeanFactoryMetaData beanFactoryMetaData) {
		this.beanFactoryMetaData = beanFactoryMetaData;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName, Class<T> clazz) {
		BeanEntry entry = beanFactoryMetaData.getEntry(beanName);
		BeanReference reference = beanFactoryMetaData.getReference(beanName);
		
		if(entry==null)throw new NoSuchBeanException(beanName);
		
		T bean = null;
		
		/* 빈 인스턴스 화 및 DI */
		if(entry.getScope().equals(Scope.SINGLETON))
		{
			bean = singletonRegistry.getBean(beanName, clazz);
		}
		else if(entry.getScope().equals(Scope.PROTOTYPE))
		{
			bean = singletonRegistry.getEntryObjectMapper().mapping(entry, clazz);
		}

		if(reference!=null)
		{
			BeanEntry refEntry = beanFactoryMetaData.getEntry(reference.getRefName());
			T refBean = (T)getBean(refEntry.getBeanName(), refEntry.getBeanType());
			ReflectionUtils.setField(bean, reference.getInjectField(), refBean);
		}
		
		/* Aware 확인 */
		if(ReflectionUtils.isImplements(entry.getBeanType(), BeanNameAware.class))
		{
			ReflectionUtils.invoke(bean, "setBeanName", entry.getBeanName());
		}
		
		if(ReflectionUtils.isImplements(entry.getBeanType(), BeanFactoryAware.class))
		{
			ReflectionUtils.invoke(ReflectionUtils.findMethod(entry.getBeanType(), "setBeanFactory", AbstractBeanFactory.class), bean, this);
		}
		
		if(ReflectionUtils.isImplements(entry.getBeanType(), ApplicationContextAware.class))
		{
			ReflectionUtils.invoke(ReflectionUtils.findMethod(entry.getBeanType(), "setApplicationContext", ApplicationContext.class), bean, applicationContext);
		}
		
		//entry.getBeanType().inte
		/* Lifecycle 콜백 */
		// PostConstruct -> InitializingBean -> xml에 init-method
		
		return bean;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
