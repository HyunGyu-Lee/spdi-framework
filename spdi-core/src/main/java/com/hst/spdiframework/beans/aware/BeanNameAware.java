package com.hst.spdiframework.beans.aware;

import com.hst.spdiframework.context.Aware;

public interface BeanNameAware extends Aware {
	public void setBeanName(String beanName);
}
