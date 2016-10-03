package com.leeframework.beans.aware;

import com.leeframework.beans.factory.AbstractBeanFactory;
import com.leeframework.context.Aware;

public interface BeanFactoryAware extends Aware {
	public void setBeanFactory(AbstractBeanFactory beanFactory);
}
