package com.spdiframework.beans.aware;

import com.spdiframework.context.Aware;

public interface BeanNameAware extends Aware {
	public void setBeanName(String beanName);
}
