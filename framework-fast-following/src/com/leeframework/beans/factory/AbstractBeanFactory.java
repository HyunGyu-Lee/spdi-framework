package com.leeframework.beans.factory;

import com.leeframework.beans.BeanEntry;
import com.leeframework.beans.BeanFactoryMetaData;
import com.leeframework.beans.Scope;
import com.leeframework.beans.SingletonRegistry;
import com.leeframework.beans.exception.NoSuchBeanException;

public abstract class AbstractBeanFactory {
	
	private BeanFactoryMetaData beanFactoryMetaData;
	private SingletonRegistry singletonRegistry = new SingletonRegistry();
	
	public AbstractBeanFactory(BeanFactoryMetaData beanFactoryMetadata) {
		this.beanFactoryMetaData = beanFactoryMetadata;
		for(String k : beanFactoryMetadata.getBeanEntries().keySet())
		{
			if(beanFactoryMetadata.getBeanEntries().get(k).getScope().equals(Scope.SINGLETON))
			{
				singletonRegistry.registry(beanFactoryMetadata.getBeanEntries().get(k));
			}
		}
		System.out.println("전달받은 메타데이터를 통해 Scope가 싱글톤(디폴트)인 것들은 싱글톤레지스트리에 적재");
	}
	public BeanFactoryMetaData getBeanFactoryMetaData() {
		return beanFactoryMetaData;
	}
	public void setBeanFactoryMetaData(BeanFactoryMetaData beanFactoryMetaData) {
		this.beanFactoryMetaData = beanFactoryMetaData;
	}

	public <T> T getBean(String beanName, Class<T> clazz) {
		BeanEntry entry = beanFactoryMetaData.getEntry(beanName);
		
		if(entry==null)throw new NoSuchBeanException(beanName);
		
		if(entry.getScope().equals(Scope.SINGLETON))
		{
			return singletonRegistry.getBean(beanName, clazz);
		}
		else if(entry.getScope().equals(Scope.PROTOTYPE))
		{
			return null;
		}
		else
		{
			return null;
		}
	}
}
