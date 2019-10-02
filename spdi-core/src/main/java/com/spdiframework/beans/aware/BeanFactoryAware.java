package com.spdiframework.beans.aware;

import com.spdiframework.beans.factory.AbstractBeanFactory;
import com.spdiframework.context.Aware;

public interface BeanFactoryAware extends Aware {
	public void setBeanFactory(AbstractBeanFactory beanFactory);
}
