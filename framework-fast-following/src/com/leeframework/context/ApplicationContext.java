package com.leeframework.context;

import com.leeframework.beans.BeanFactoryMetaData;
import com.leeframework.beans.factory.AbstractBeanFactory;
import com.leeframework.beans.factory.BeanFactory;

public abstract class ApplicationContext {
	
	private AbstractBeanFactory beanFactory;
	
	public void initailize() {
		System.out.println("하위컨텍스트에서 정의한 메타데이터생성전략에 따라 beanFactory 생성");
		beanFactory = new BeanFactory(createBeanFactoryMetaDataStrategy());
	}
	
	public abstract BeanFactoryMetaData createBeanFactoryMetaDataStrategy();
	
	public <T> T getBean(String beanName, Class<T> clazz) { 
		System.out.println(beanFactory);
		return beanFactory.getBean(beanName, clazz);
	}
	
}
