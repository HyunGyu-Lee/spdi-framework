package com.spdiframework.context;

/***
 * @author dlgusrb0808@gmail.com
 */
public interface ApplicationContextAware extends Aware {
	void setApplicationContext(ApplicationContext applicationContext);
}
