package com.hst.spdiframework.beans.aware;

import com.hst.spdiframework.beans.factory.AbstractBeanFactory;
import com.hst.spdiframework.context.Aware;

public interface BeanFactoryAware extends Aware {
	public void setBeanFactory(AbstractBeanFactory beanFactory);
}
