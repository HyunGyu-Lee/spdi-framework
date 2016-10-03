package com.leeframework.beans.aware;

import com.leeframework.context.Aware;

public interface BeanNameAware extends Aware{
	public void setBeanName(String beanName);
}
