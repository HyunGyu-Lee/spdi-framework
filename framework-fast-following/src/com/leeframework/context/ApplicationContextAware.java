package com.leeframework.context;

public interface ApplicationContextAware extends Aware {
	public void setApplicationContext(ApplicationContext applicationContext);
}
