package com.hst.spdiframework.context;

public interface ApplicationContextAware extends Aware {
	public void setApplicationContext(ApplicationContext applicationContext);
}
