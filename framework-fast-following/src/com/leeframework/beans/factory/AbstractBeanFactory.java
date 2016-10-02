package com.leeframework.beans.factory;

import com.leeframework.beans.SingletonRegistry;
import com.leeframework.beans.exception.NoSuchBeanException;
import com.leeframework.beans.metadata.BeanEntry;
import com.leeframework.beans.metadata.BeanFactoryMetaData;
import com.leeframework.beans.metadata.BeanReference;
import com.leeframework.beans.metadata.Scope;
import com.leeframework.utils.ReflectionUtils;

public abstract class AbstractBeanFactory {
	
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
		
		return bean;
	}
	
	public void inject() {
		
	}
	
}
